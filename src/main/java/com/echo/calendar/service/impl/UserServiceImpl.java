package com.echo.calendar.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.calendar.dao.UserDAO;
import com.echo.calendar.entity.pojo.UserEntity;
import com.echo.calendar.entity.vo.UserLoginVO;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, UserEntity> {


    public UserLoginVO login(String code) throws JSONException {
        //接收从客户端获取的code
        //向微信后台发起请求获取openid的url
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", "------------------").//填写自己的appid
                replace("SECRET", "----------------------").replace("JSCODE", code).//填写自己的appsecret，
                replace("authorization_code", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();
        String returnValue=restTemplate.getForObject(requestUrl,String.class);//Get请求微信接口

        //从接受到的字符串里提取openid和session_key
        //先创建一个json对象
        JSONObject jsonObject = new JSONObject();
        jsonObject=(JSONObject) JSON.parse(returnValue);
        String openid=(String)jsonObject.get("openid");
        String session_key=(String)jsonObject.get("session_key");

        UserLoginVO userLoginVO = new UserLoginVO(openid, session_key);
        return userLoginVO;
    }
}
