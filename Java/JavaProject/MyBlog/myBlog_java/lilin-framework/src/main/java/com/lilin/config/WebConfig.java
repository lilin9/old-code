package com.lilin.config;

import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by LiLin on 2022/9/4/16:14:44
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * @Author lilin
     * @Date 2022/9/6 09:05:49
     * @Return
     * @Description 跨域请求设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //是否允许 cookie
                .allowCredentials(true)
                //设置允许请求的方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //设置允许请求的 header 属性
                .allowedHeaders("*")
                //设置请求最大时间
                .maxAge(3600);
    }

    /**
     * @Author lilin
     * @Date 2022/9/6 14:53:17
     * @Return
     * @Description 通过 fastjson 对日期进行格式化的配置
     *
     * 注：因前端原因，这里就不对日期进行配置了
     */
    //@Bean   //通过 @Bean 注入 fastJsonHttpMessageConvert
    public HttpMessageConverter fastJsonHttpMessageConverters() {
        //1.需要定义一个转换消息的对象： converter
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        SerializeConfig.getGlobalInstance().put(Long.class, (ObjectSerializer) ToStringSerializer.instance);

        fastJsonConfig.setSerializeConfig(SerializeConfig.getGlobalInstance());
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverters());
    }
}













