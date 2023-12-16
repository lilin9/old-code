package com.mrli.springboot2.config;

import com.mrli.springboot2.bean.Pet;
import com.mrli.springboot2.converter.GuiguConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/20/10:00
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig/* implements WebMvcConfigurer*/ {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //自定义内容协商策略
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                //指定支持解析的参数对应的媒体类型
                mediaTypeMap.put("json", MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml", MediaType.APPLICATION_XML);
                mediaTypeMap.put("gg", MediaType.parseMediaType("application/x-guigu"));

                //基于参数
                ParameterContentNegotiationStrategy paramStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);
                //基于请求头
                HeaderContentNegotiationStrategy headStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(List.of(paramStrategy, headStrategy));
            }

            //添加消息匹配器 messageConverter
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiguConverter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //不移除“;”后面的内容，是矩阵变量的内容生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /*
            自定义convert

            new Converter<S, T>(){}
                S：传进来的原类型；
                T：需要转换成的类型
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter((Converter<String, Pet>) source -> {
                    //cat,2
                    if (!StringUtils.isEmpty(source)) {
                        String[] split = source.split(",");
                        Pet pet = new Pet();
                        pet.setName(split[0]);
                        pet.setAge(Integer.parseInt(split[1]));
                        return pet;
                    }
                    return null;
                });
            }
        };
    }

/*    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //不移除“;”后面的内容，是矩阵变量的内容生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }*/
}
