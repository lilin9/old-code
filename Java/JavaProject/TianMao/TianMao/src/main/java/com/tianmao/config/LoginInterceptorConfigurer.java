package com.tianmao.config;

import com.tianmao.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by LiLin on 2022/05/11/11:26
 * <p>
 * 注册登录拦截器，并进行一些配置
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //配置拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        //注册拦截器
        registry.addInterceptor(interceptor)
                //对所有的请求都进行拦截
                .addPathPatterns("/**")
                //添加拦截白名单
                .excludePathPatterns(
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/users/login",
                        "/users/register",
                        "/users/reset",
                        "/product/**",
                        "/mails/getCode",
                        "/dictDistrict/**",
                        "/index.html",
                        "/login.html",
                        "/register.html",
                        "/search.html",
                        "/recallPassword.html");
    }
}
