package com.MrLi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.MrLi.admin.servlet")
@SpringBootApplication
public class SpringBoot3Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3Application.class, args);
    }
}
