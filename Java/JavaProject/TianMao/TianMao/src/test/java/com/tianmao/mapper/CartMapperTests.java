package com.tianmao.mapper;

import com.tianmao.pojo.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by LiLin on 2022/05/12/16:52
 *
 * CartMapper.java类的测试文件类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void selectCartByUid() {
        System.out.println(cartMapper.selectCartVoByUid(8));
    }

    @Test
    public void selectCartByCid() {
        System.out.println(cartMapper.selectCartByCid(1));
    }

    @Test
    public void updateCartNumByCid() {
        System.out.println("影响力" + cartMapper.updateCartNumByCid(1, 5, "admin", new Date()) + "行");
    }

    @Test
    public void deleteCartByCid() {
        System.out.println(cartMapper.deleteCartByCid(1));
    }

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setNum(1);
        cart.setPid(10000020);
        cart.setUid(8);
        cart.setPrice(5119L);
        cartMapper.insert(cart);
    }
}
