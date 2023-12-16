package com.MrLi.admin.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by MrLi on 2022/03/25/14:30
 */

//proxyBeanMethods = true：保证依赖的组件始终是单实例
@Configuration()
public class MyRegistrationBeanConfig {
    //servlet
    @Bean
    public ServletRegistrationBean<MyServlet> myServlet() {
        return new ServletRegistrationBean<>(new MyServlet(), "/my", "my1");
    }

    //filter
    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my", "/css/*"));
        return filterRegistrationBean;
    }

    //listener
    @Bean
    public ServletListenerRegistrationBean<MyServletContentListener> myListener() {
        return new ServletListenerRegistrationBean<>(new MyServletContentListener());
    }
}
