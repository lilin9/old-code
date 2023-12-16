package com.lilin.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by LILIN on 2023/7/24/16:05:34
 */
@Component
@Slf4j
public class ReceiveMessage {
    //延迟队列一定要接受的是死信队列的消息才行
    @RabbitListener(queues = {"queue.delay.dead.1"})
    public void receiveMessage(Message message) {
        String body = new String(message.getBody());
        log.info("接收到的消息为：{}, 接收时间为：{}.", body, new Date());
    }
}
