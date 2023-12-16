package com.MrLi.admin.config;

import com.MrLi.admin.interceptor.LoginInterceptor;
import com.MrLi.admin.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by MrLi on 2022/03/24/9:13
 *
 * 配置类
 *
 * @EnableWebMvc：全面接管SpringMVC
 *      指静态资源、视图解析器、欢迎页等全部失效
 */
@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    /*
    面试题：Filter、Interceptor几乎具有相同的功能，它们的区别又是什么？
        1.Filter是Servlet定义的原生组件。它的好处是哪怕脱离Spring应用也可以正常使用
        2.Interceptor是Spring定义的接口。它的好处是可以使用Spring的自动装配等功能
     */
    @Autowired
    RedisUrlCountInterceptor interceptor;

    //定义静态资源行为
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
        访问 /aa/** 所有请求都去 classpath:/static/ 下面进行匹配
         */
        registry.addResourceHandler("/aa/**")
                .addResourceLocations("classpath:/static/");
    }

    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")     //所有请求都会被拦截
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");    //设置不会被拦截的请求

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");
    }
}
