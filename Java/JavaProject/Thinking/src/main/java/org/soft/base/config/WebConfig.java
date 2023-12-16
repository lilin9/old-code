package org.soft.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.soft.base.interceptor.ArticlesClickInterceptor;
import org.soft.base.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by LILIN on 2023/8/1/10:56:59
 * 配置类
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private ArticlesClickInterceptor articlesClickInterceptor;
    @Resource
    private LoginInterceptor loginInterceptor;

    //把spring-boot默认的json解析器由Jenkins换为fastjson
    @Bean
    public HttpMessageConverters fastjsonConverters() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //自定义格式化输出
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect);

        FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
        fastjson.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastjson);
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册拦截器");
        //添加 redis 拦截器
        registry
                //添加拦截器
                .addInterceptor(articlesClickInterceptor)
                //添加拦截路径
                .addPathPatterns("/article/articleById/*");

        //添加登录拦截器
        registry
                .addInterceptor(loginInterceptor)
                .addPathPatterns("/");

    }
}