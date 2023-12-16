package com.MrLi.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by MrLi on 2022/03/26/15:37
 */
@Deprecated
//@Configuration
//@ConfigurationProperties("spring.datasource")
public class MyDataSourceConfig {
    //配置 druid web监控
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(List.of("/*"));
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    //配置 druid 监控页功能
    //@Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        return new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    }

    //配置数据源
    //@Bean
    public DataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        //加入监控功能
        dataSource.setFilters("stat");
        return dataSource;
    }
}
