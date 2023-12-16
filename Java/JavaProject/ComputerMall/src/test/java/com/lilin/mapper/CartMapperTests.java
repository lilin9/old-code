package com.lilin.mapper;

import com.lilin.pojo.Cart;
import com.lilin.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/15/13:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart(null, 6, 10000001, 46L, 1);
        cartMapper.insert(cart);
    }

    @Test
    public void selectCartByUidAndPid() {
        System.out.println(cartMapper.selectCartByUidAndPid(6, 10000001));
    }

    @Test
    public void updateCartNum() {
        cartMapper.updateCartNum(1, 10, "admin", new Date());
    }

    @Test
    public void selectProductByUid() {
        List<CartVo> list = cartMapper.selectVOByUid(6);
        list.forEach(System.out :: println);
    }

    @Test
    public void selectCartByCid() {
        System.out.println(cartMapper.selectCartByCid(5));
    }

    @Test
    public void selectVOByCid() {
        cartMapper.selectVOByCidList(new Integer[]{1, 2, 3, 4, 5}).forEach(System.out :: println);
    }
}
