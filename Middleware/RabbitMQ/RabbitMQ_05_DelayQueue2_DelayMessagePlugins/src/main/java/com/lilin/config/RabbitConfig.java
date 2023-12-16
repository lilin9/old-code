package com.lilin.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LILIN on 2023/7/21/11:41:05
 */
@Configuration
public class RabbitConfig {
    //延迟交换机
    @Value("${my.exchangeName}")
    private String exchangeName;

    //延迟队列
    @Value("${my.queueDelayName}")
    private String queueDelayName;

    //创建延迟交换机
    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(exchangeName, "x-delayed-message", true, false, arguments);
    }

    //创建延迟队列
    @Bean
    public Queue delayQueue() {
        return QueueBuilder
                .durable(queueDelayName)   //队列名称
                .build();
    }

    //绑定交换机和正常队列
    @Bean
    public Binding binding(CustomExchange customExchange, Queue delayQueue) {
        //绑定交换机和队列，也需要指定路由key plugin，然后 noargs
        return BindingBuilder.bind(delayQueue).to(customExchange).with("plugin").noargs();
    }
}
