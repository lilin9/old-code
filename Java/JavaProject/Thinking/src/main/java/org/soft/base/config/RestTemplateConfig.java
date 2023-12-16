package org.soft.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by LILIN on 2023/8/8/17:27:32
 */
@Configuration
public class RestTemplateConfig {
    @Resource
    private CustomHttpMessageConvert customHttpMessageConvert;
    @Bean
    public RestTemplate initRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //注册自定义消息序列化器
        restTemplate.getMessageConverters().set(5, customHttpMessageConvert);

        return restTemplate;
    }
}