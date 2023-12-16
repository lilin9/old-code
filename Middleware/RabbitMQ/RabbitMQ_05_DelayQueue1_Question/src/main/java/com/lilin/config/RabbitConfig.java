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
    //交换机（既是正常交换机，也是死信交换机）
    @Value("${my.exchangeName}")
    private String exchangeName;

    //正常队列
    @Value("${my.queueNormalName}")
    private String queueNormalName;

    //死信队列
    @Value("${my.queueDeadName}")
    private String queueDeadName;

    //创建交换机
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(exchangeName).build();
    }

    //创建正常队列
    @Bean
    public Queue normalQueue() {
        return QueueBuilder
                .durable(queueNormalName)   //队列名称
//                .ttl(20000)                 //队列过期时间
                .deadLetterExchange(exchangeName)    //设置死信交换机，正常交换机和死信交换机是同一个
                .deadLetterRoutingKey("error")  //死信路由 key
                .build();
    }

    //绑定交换机和正常队列
    @Bean
    public Binding bindingNormal(DirectExchange directExchange, Queue normalQueue) {
        return BindingBuilder.bind(normalQueue).to(directExchange).with("order");
    }

    //创建死信队列
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(queueDeadName).build();
    }

    //绑定交换机和死信队列
    @Bean
    public Binding bindingDead(DirectExchange directExchange, Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(directExchange).with("error");
    }
}
