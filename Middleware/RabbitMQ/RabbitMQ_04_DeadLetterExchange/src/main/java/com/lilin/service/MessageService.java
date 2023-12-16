package com.lilin.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
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
        Message msg = MessageBuilder.withBody("hello, dlx".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.Normal.a", "order", msg);
        log.info("消息发送成功，发送时间：{}", new Date());
    }

}
