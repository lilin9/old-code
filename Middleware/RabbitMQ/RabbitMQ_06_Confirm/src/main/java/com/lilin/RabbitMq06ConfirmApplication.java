package com.lilin;

import com.lilin.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMq06ConfirmApplication implements ApplicationRunner {
    @Resource
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMq06ConfirmApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMsg();
    }
}
