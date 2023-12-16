package com.lilin.service;

import com.lilin.config.MyConfirmCallback;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by LILIN on 2023/7/25/14:48:27
 */
@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private MyConfirmCallback confirmCallback;

    @PostConstruct  //构造方法后就执行，相当于初始化作用
    public void init() {
        rabbitTemplate.setConfirmCallback(confirmCallback);
    }

    public void sendMsg() {
        Message message = MessageBuilder.withBody("hello confirm".getBytes()).build();

        //关联数据
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("order_123");

        rabbitTemplate.convertAndSend("exchange.confirm.11", "info", message, correlationData);
        log.info("消息发送完毕，发送时间为 {}", new Date());
    }
}





