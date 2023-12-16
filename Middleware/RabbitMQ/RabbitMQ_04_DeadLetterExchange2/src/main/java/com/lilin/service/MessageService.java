package com.lilin.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by LILIN on 2023/7/21/15:17:20
 */
@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        //设置单条消息过期时间
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("15000");   //设置过期时间是 15s

        //将过期属性设置到消息上
        Message msg = MessageBuilder
                .withBody("hello, dlx".getBytes())
                .andProperties(messageProperties)
                .build();
        rabbitTemplate.convertAndSend("exchange.Normal.2", "order", msg);
        log.info("消息发送成功，发送时间：{}", new Date());
    }

}
