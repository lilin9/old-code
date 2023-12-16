package com.lilin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiLin on 2022/04/12/11:44
 *
 * 地址业务层单元测试方法
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    OrderService orderService;

    @Test
    public void createOrder() {
        System.out.println(orderService.createOrder(7, 6, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, "amy"));
    }
}
