package com.lilin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication
@MapperScan("com.lilin.mapper")
public class MyBlogRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogRefactorApplication.class, args);
    }

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        //创建配置的工厂类对象
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //设置上传的文件大小
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));

        //通过工厂类来创建 MultipartConfigElement 对象
        return factory.createMultipartConfig();
    }

}
