package com.tianmao.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiLin on 2022/05/12/17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    CartService cartService;

    @Test
    public void selectCartVoByUid() {
        System.out.println(cartService.selectCartVoByUid(8));
    }

    @Test
    public void updateCartNumByCid() {
        System.out.println("影响了" + cartService.updateCartNum(8, 2, 20, "amy") + "行");
    }

    @Test
    public void deleteCartByCid() {
        cartService.deleteCartByCid(8, 2);
    }

    @Test
    public void insert() {
        cartService.insert(8, 10000019, "amy", 1, 4899L);
    }
}
