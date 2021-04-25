package com.echo.calendar.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WechatUtil {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;

    public JSONObject getSessionKeyAndOpenId(String code) {
        //向微信后台发起请求获取openid的url
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", appid).//填写自己的appid
                replace("SECRET", appsecret).replace("JSCODE", code).//填写自己的appsecret，
                replace("authorization_code", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();
        String returnValue = restTemplate.getForObject(requestUrl, String.class);  //Get请求微信接口

        return (JSONObject) JSON.toJSON(returnValue);
    }
}