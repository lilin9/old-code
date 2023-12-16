package com.lilin.springboot5.config;

import com.lilin.springboot5.bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by LiLin on 2022/03/30/14:36
 */
@Configuration
public class MyConfig {
    @Profile("prod")
    @Bean
    public Color red() {
        return new Color();
    }

    @Profile("test")
    @Bean
    public Color green() {
        return new Color();
    }
}
