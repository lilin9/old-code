package com.lilin.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by LILIN on 2023/7/21/14:26:40
 */
@Configuration
public class RabbitConfig {
    @Value("${my.exchangeNormal}")
    private String exchangeNormal;  //正常交换机
    @Value("${my.queueNormal}")
    private String queueNormal; //正常队列

    @Value("${my.exchangeDead}")
    private String exchangeDead;   //死信交换机
    @Value("${my.queueDead}")
    private String queueDead;   //死信队列


    //创建正常队列和正常交换机
    @Bean
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange(exchangeNormal).build();
    }
    @Bean
    public Queue normalQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //设置过期时间为 20s
        arguments.put("x-message-ttl", 20000);
        //当产生死信时，让死信进入死信交换机，而不是立即删除
        arguments.put("x-dead-letter-exchange", exchangeDead);
        //设置死信路由 key，要保证和死信交换机-死信队列绑定的 key 一致
        arguments.put("x-dead-letter-routing-key", "error");

        return QueueBuilder.durable(queueNormal)
                .withArguments(arguments)   //设置过期时间
                .build();
    }

    //绑定正常队列和正常交换机
    @Bean
    public Binding bindingNormal(DirectExchange normalExchange,
                                 Queue normalQueue) {
        return BindingBuilder.bind(normalQueue).to(normalExchange)
                .with("order");
    }


    //创建死信队列和死信交换机
    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange(exchangeDead).build();
    }
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(queueDead).build();
    }

    //绑定死信队列和死信交换机
    @Bean
    public Binding bindingDead(DirectExchange deadExchange,
                               Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange)
                .with("error");
    }
}
