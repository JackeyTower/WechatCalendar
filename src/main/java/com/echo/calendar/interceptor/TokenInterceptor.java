package com.echo.calendar.interceptor;

import com.echo.calendar.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 18:18
 * @Version 1.0
 * 全局校验Token
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public TokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * 通过拦截器对请求头进行校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                //获得token
                String token = header.substring(7);
                //验证token
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String session_key = (String) claims.get("session_key");
                    if (session_key != null) {
                        request.setAttribute("openid",claims.getId());
                        request.setAttribute("session_key",session_key);
                        return true;
                    } else {
                        throw new BadCredentialsException("令牌已失效");
                    }
                } catch (Exception e) {
                    throw new BadCredentialsException("令牌已失效");
                }
            }
        }
        throw new AuthenticationCredentialsNotFoundException("请先登录");
    }
}
/**
 * 前端每次请求时token应该是放置于请求头中键名为Authorization, 值为Bearer Token(Token为服务端生成的token)
 *首先我们使用@Component注解将该类注册为一个Spring容器，然后使用该类实现 HandlerInterceptor接口，
 * preHandle说明我们是在目标方法执行前进行处理。我们先通过构造器的方式将JwtUtil注入便于后续使用，然后从请求头中读取token，
 * 如果token为空则说明用户没有登陆，直接抛出异常即可，如果在解析token出现错误直接抛出错误即可。
 * 这一个部分的主要思路就是如果不能正确的解析出token的内容则返回给前端错误信息，让前端跳转到登录页面进行重新登录，
 * 如果能够正确解析出我们存储的用户id和用户角色这两个内容则添加到请求request域里面去，后续我们在接口处的直接可以从request中读到用户iduid和用户角色roles。
 * 这样我们就可以判断每次访问时用户是否已经登陆了，并且我们将用户id以及用户角色都存于request中便于我们之后controller的开发。
 */
