package com.lilin.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by LILIN on 2023/7/20/15:02:05
 */
@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        //使用建造者模式创建消息
        Message msg = MessageBuilder.withBody("hello rabbitmq".getBytes()).build();
        //参数1 交换机，参数2 队列，参数3 消息体
        rabbitTemplate.convertAndSend("exchange.direct", "info", msg);
        log.info("消息发送完毕，发生时间是: {}", new Date());
    }
}
