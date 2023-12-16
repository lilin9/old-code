package com.lilin.service;

import com.lilin.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/15:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    CartService cartService;

    @Test
    public void insert() {
        cartService.insert(6, 10000001, 1, "amy");
    }

    @Test
    public void selectVOByUid() {
        List<CartVo> list = cartService.selectVOByUid(6);
        list.forEach(System.out :: println);
    }

    @Test
    public void addCartNum() {
        System.out.println(cartService.addCartNum(1, 6, "amy"));
    }

    @Test
    public void selectVOByCidList() {
        cartService.selectVOByCidList(6, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .forEach(System.out :: println);
    }
}
