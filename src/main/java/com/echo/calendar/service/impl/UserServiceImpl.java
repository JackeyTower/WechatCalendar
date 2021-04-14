package com.echo.calendar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.calendar.dao.UserDAO;
import com.echo.calendar.entity.dto.OpenIdAndSessionKey;
import com.echo.calendar.entity.pojo.UserEntity;
import com.echo.calendar.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, UserEntity> {

    private JwtUtil jwtUtil;
    private UserDAO userDAO;
    private WechatUtil wechatUtil;
    private IdWorker idWorker;
    private RedisUtil redisUtil;

    public UserServiceImpl(JwtUtil jwtUtil, UserDAO userDAO, WechatUtil wechatUtil, IdWorker idWorker, RedisUtil redisUtil) {
        this.jwtUtil = jwtUtil;
        this.userDAO = userDAO;
        this.wechatUtil = wechatUtil;
        this.idWorker = idWorker;
        this.redisUtil = redisUtil;
    }

    //传入code,rawData,signature，接收token
    public String login(String code,
                        String rawData,
                        String signature) throws JSONException {
        //已接收到从客户端获取的code，rawData,signature
        //先对rawData进行处理
        JSONObject rawDataJson = (JSONObject) JSON.toJSON(rawData);
        //开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = wechatUtil.getSessionKeyAndOpenId(code);
        String openid = SessionKeyOpenId.getString("openid");
        String session_key = SessionKeyOpenId.getString("session_key");
        // 校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData+session_key);
        if (!signature.equals(signature2)) {
            return "";
        }

        //生成一个唯一skey，用于维护微信小程序用户与服务端的会话。
        String skey = idWorker.nextId()+"";
        //skey为key，openid和session_key封装成的新对象作为value，存入缓存
        OpenIdAndSessionKey openIdAndSessionKey = new OpenIdAndSessionKey(openid,session_key);
        redisUtil.set(skey,openIdAndSessionKey);

        // 根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        UserEntity user = userDAO.selectById(openid);
        if(user==null){
            UserEntity newUser = new UserEntity();
            newUser.setOpenid(openid);
            newUser.setSkey(skey);
            newUser.setCreate_time(DateTimeTransferUtil.getNowTimeStamp());
            newUser.setLast_visit_time(DateTimeTransferUtil.getNowTimeStamp());
            newUser.setSession_key(openIdAndSessionKey.getSession_key());
            newUser.setCity(rawDataJson.getString("city"));
            newUser.setCountry(rawDataJson.getString("country"));
            newUser.setProvince(rawDataJson.getString("province"));
            newUser.setGender(rawDataJson.getString("gender"));
            newUser.setAvatar_url(rawDataJson.getString("avatarUrl"));
            newUser.setNickname(rawDataJson.getString("nickName"));
        }else {
            //老用户，更新登陆时间
            user.setLast_visit_time(DateTimeTransferUtil.getNowTimeStamp());
            //重置会话skey,这里用缓存，似乎没必要再存一次
            user.setSkey(skey);
            userDAO.updateById(user);
        }





//        //向微信后台发起请求获取openid的url
//        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
//
//        //替换appid，appsecret，和code
//        String requestUrl = WX_URL.replace("APPID", "wxa2d1b0549f394728").//填写自己的appid
//                replace("SECRET", "e4a4c911c5db9b2097b834283f3af5cb").replace("JSCODE", code).//填写自己的appsecret，
//                replace("authorization_code", "authorization_code");
//
//        RestTemplate restTemplate = new RestTemplate();
//        String returnValue=restTemplate.getForObject(requestUrl,String.class);//Get请求微信接口
//
//        //从接受到的字符串里提取openid和session_key
//        //先创建一个json对象
//        JSONObject jsonObject = new JSONObject();
//        jsonObject=
//        String openid=(String)jsonObject.get("openid");
//        String session_key=(String)jsonObject.get("session_key");

        return jwtUtil.createJWTbySkey(skey);
    }
}
