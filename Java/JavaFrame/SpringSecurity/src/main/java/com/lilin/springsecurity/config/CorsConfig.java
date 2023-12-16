package com.lilin.springsecurity.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by LiLin on 2022/7/10/14:22:14
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名类型
                .allowedOriginPatterns("*")
                //是否允许cookie
                .allowCredentials(true)
                //设置允许请求的方式
                .allowedMethods("GET","POST","PUT","DELETE")
                //设置蕴蓄的header属性
                .allowedHeaders("*")
                //允许跨域时间
                .maxAge(3600);
    }
}