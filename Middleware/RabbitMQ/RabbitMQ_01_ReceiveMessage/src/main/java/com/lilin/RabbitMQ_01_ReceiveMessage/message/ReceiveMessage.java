package com.lilin.RabbitMQ_01_ReceiveMessage.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by LILIN on 2023/7/20/14:05:31
 */
@Component
@Slf4j
public class ReceiveMessage {

    //监听 a, b 队列消息
    @RabbitListener(queues = {"queue.fanout.a", "queue.fanout.b"})
    public void receiveMessage(Message message) {
        //获取消息体
        byte[] body = message.getBody();
        //将消息体转换为字符数组
        String msg = new String(body);
        log.info("接收到的消息：{}", msg);
    }
}
