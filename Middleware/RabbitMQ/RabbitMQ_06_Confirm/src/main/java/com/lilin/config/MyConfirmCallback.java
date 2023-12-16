package com.lilin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by LILIN on 2023/7/25/14:56:18
 */
@Component
@Slf4j
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息成功到达交换机");
            return;
        }

        log.error("消息没有到达交换机，原因是：{}", cause);
    }
}
