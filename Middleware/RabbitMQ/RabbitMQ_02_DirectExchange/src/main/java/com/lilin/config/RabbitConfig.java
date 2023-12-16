package com.lilin.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LILIN on 2023/7/20/14:46:57
 */
@Configuration
//@ConfigurationProperties(prefix = "my")
public class RabbitConfig {
    //交换机名字
    @Value("${my.exchangeName}")
    private String exchangeName;
    //队列A的名字
    @Value("${my.queueAName}")
    private String queueAName;
    //队列A的名字
    @Value("${my.queueBName}")
    private String queueBName;

    //创建交换机三步骤
    //1.定义交换机
    @Bean
    public DirectExchange directExchange() {
        //使用建造者模式
        return ExchangeBuilder.directExchange(exchangeName).build();
    }

    //2.定义队列
    @Bean
    public Queue queueA() {
        //使用建造者模式
        return QueueBuilder.durable(queueAName).build();
    }

    @Bean
    public Queue queueB() {
        //使用建造者模式
        return QueueBuilder.durable(queueBName).build();
    }

    //3.绑定交换机和队列
    @Bean
    public Binding bindingA(DirectExchange directExchange, Queue queueA) {
        //绑定队列A到直连交换区
        return BindingBuilder.bind(queueA).to(directExchange).with("error");
    }

    @Bean
    public Binding bindingB1(DirectExchange directExchange, Queue queueB) {
        //绑定队列B到直连交换区
        return BindingBuilder.bind(queueB).to(directExchange).with("error");
    }
    @Bean
    public Binding bindingB2(DirectExchange directExchange, Queue queueB) {
        //绑定队列B到直连交换区
        return BindingBuilder.bind(queueB).to(directExchange).with("info");
    }
    @Bean
    public Binding bindingB3(DirectExchange directExchange, Queue queueB) {
        //绑定队列B到直连交换区
        return BindingBuilder.bind(queueB).to(directExchange).with("warning");
    }
}
