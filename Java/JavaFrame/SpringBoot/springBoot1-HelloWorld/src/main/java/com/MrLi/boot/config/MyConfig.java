package com.MrLi.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.MrLi.boot.bean.Car;
import com.MrLi.boot.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by MrLi on 2022/03/16/12:36
 */

/*
1、配置类里面使用 @Bean 标注在方法上给容器注册组件，默认是单实例
2、配置类本身也是组件
3、proxyBeanMethods：代理bean的方法
4、外部无论对配置类中的这个组件注册方法调用多少次获取都是之前注册容器中的单实例对象
5、方法返回的值，就是组件在容器中的实例
 */

@Import({User.class, DBHelper.class})//给容器中自动创建出这两个类型的组件
@Configuration(proxyBeanMethods = true) //将这个类声明为 SpringBoot 的配置类 == 配置文件

/*
1、开启Car的配置绑定功能
2、将Car这个组件自动注册到容器中
 */
@EnableConfigurationProperties(Car.class)
public class MyConfig {
    @Bean//往容器中添加组件，以方法名作为组件的id
    public User user1() {
        return new User(1, "user1");
    }

    @Bean("smith")
    public User user2() {
        return new User(2, "user2");
    }
}
