package com.lilin.springboot5.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by LiLin on 2022/03/31/15:25
 */

//可以设置应用启动时做一些一次性操作
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner ---> run");
    }
}
