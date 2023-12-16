package org.soft.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.soft.base.mapper")
public class SsmDemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmDemApplication.class, args);
    }

}
