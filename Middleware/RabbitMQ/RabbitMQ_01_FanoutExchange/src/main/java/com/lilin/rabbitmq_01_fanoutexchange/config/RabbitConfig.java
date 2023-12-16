package com.lilin.rabbitmq_01_fanoutexchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LILIN on 2023/7/20/10:45:41
 */
@Configuration
public class RabbitConfig {
    //1.定义交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("exchange.fanout");
    }

    //2.定义队列
    @Bean
    public Queue queueA() {
        return new Queue("queue.fanout.a");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queue.fanout.b");
    }

    //3.绑定交换机和队列
    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange, Queue queueA) {
        //绑定队列A到扇形交换区
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange, Queue queueB) {
        //绑定队列B到扇形交换区
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
