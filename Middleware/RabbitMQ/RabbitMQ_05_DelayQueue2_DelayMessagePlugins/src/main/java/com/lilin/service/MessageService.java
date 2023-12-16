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
 * Created by LILIN on 2023/7/21/11:53:00
 */
@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        {
            MessageProperties messageProperties = new MessageProperties();
            //过期时间
            messageProperties.setHeader("x-delay", 25000);
            //创建消息，并将过期时间设置到消息对象内
            Message message = MessageBuilder
                    .withBody("hello delay queue, timeout is 25000".getBytes())
                    .andProperties(messageProperties)
                    .build();
            //发送消息
            rabbitTemplate.convertAndSend("exchange.delay.2", "plugin", message);

            log.info("消息发送成功，时间是：{}", new Date());
        }
        {
            MessageProperties messageProperties = new MessageProperties();
            //过期时间
            messageProperties.setHeader("x-delay", 15000);
            //创建消息，并将过期时间设置到消息对象内
            Message message = MessageBuilder
                    .withBody("hello delay queue, timeout is 15000".getBytes())
                    .andProperties(messageProperties)
                    .build();
            //发送消息
            rabbitTemplate.convertAndSend("exchange.delay.2", "plugin", message);

            log.info("消息发送成功，时间是：{}", new Date());
        }
    }
}








