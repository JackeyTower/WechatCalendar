package com.echo.calendar.util;


/**
 * 利用了@ConfigurationProperties("jwt.config")注解
 * 从application.yml配置文件中读取了如下配置并自动注入字段
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Demon
 * @Date 2021/1/31 21.29
 * JWT生成以及校验工具类
 */
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {
    private String key;
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     *
     * @param openid session_key
     * @return
     */
    public String createJWT(String openid, String session_key) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //在这里我们将用户的id存入Jwt中，方便后续使用
        JwtBuilder builder = Jwts.builder().setId(openid)
                .setIssuedAt(now)
                //在这里我们将用户的角色存入Jwt中，方便后续鉴权,如果想存别的内容也可以往里写
                .signWith(SignatureAlgorithm.HS256, key).claim("session_key", session_key);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}

/**
 * 千万不要使用token存储用户的敏感信息，
 * token只能用于避免被篡改，而不用于加密！
 * token本身携带的内容是可以在没有服务端秘钥的情况下直接解析出来的！！！
 */