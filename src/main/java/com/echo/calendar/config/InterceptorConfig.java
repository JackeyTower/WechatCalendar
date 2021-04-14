package com.echo.calendar.config;

import com.echo.calendar.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置类
 */
@Configuration
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {

//    private final TokenInterceptor tokenInterceptor;
//
//
//    public InterceptorConfig(TokenInterceptor tokenInterceptor) {
//        this.tokenInterceptor = tokenInterceptor;
//    }
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        //拦截所有目录，除了通向login和register以及验证码的接口
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/*/login/**", "/*/register/**", "/*/verifyCode/**")
//                .excludePathPatterns("/*/*.html", "/*/*.js", "/*/*.css");
//    }
}