package com.tianmao.service;

import com.tianmao.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LiLin on 2022/05/16/11:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    OrderService orderService;

    @Test
    public void selectCartVoByCids() {
        List<CartVo> list = orderService.selectCartVoByCids(8, new ArrayList<>(Arrays.asList(10000019, 10000013)));
        list.forEach(System.out :: println);
    }

    @Test
    public void createOrder() {
        System.out.println(orderService.createOrder(1, 8, new Integer[]{7, 9}, "amy"));
    }
}
