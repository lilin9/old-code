package com.lilin.config;

import com.lilin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by LiLin on 2022/04/06/16:04
 *
 * 配处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        registry.addInterceptor(interceptor)
                //对所有请求进行拦截
                .addPathPatterns("/**")
                //添加拦截白名单
                .excludePathPatterns("/bootstrap3/**",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/web/login.html",
                        "/web/register.html",
                        "/web/product.html",
                        "/web/index.html",
                        "/index.html",
                        "/users/login",
                        "/users/register",
                        "/districts/**",
                        "/products/**");
    }
}
