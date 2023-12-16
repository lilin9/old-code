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
    @Value("${my.exchangeName}")
    private String exchangeName;

    @Value("${my.queueName}")
    private String queueName;

    //创建交换机
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(exchangeName).build();
    }

    //创建队列
    @Bean
    public Queue queue() {
        //方式1：new Queue 的方式
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 15000);
        return new Queue(queueName, true, false, false, arguments);

        //方式2：建筑者
//        return QueueBuilder
//                .durable(queueName)
//                .withArguments(arguments)
//                .build();
    }

    //绑定交换机和队列
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("info");
    }
}
