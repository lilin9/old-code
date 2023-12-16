package com.tianmao.mapper;

import com.tianmao.pojo.Order;
import com.tianmao.pojo.OrderItem;
import com.tianmao.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/16/11:39
 *
 * OrderMapper.java类的测试文件类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void selectCartVoByCids() {
        ArrayList<Integer> paramList = new ArrayList<>();
        paramList.add(1000001);
        paramList.add(10000013);
        List<CartVo> list = orderMapper.selectCartVoByCids(paramList);
        list.forEach(System.out :: println);
    }

    @Test
    public void insertOrder() {
        Order order = new Order();

        order.setUid(8);
        order.setRecvName("tom");
        order.setRecvPhone("11111111111");
        order.setRecvProvince("河北省");
        order.setRecvCity("北京");
        order.setRecvArea("景德镇");
        order.setRecvAddress("巴拉巴拉巴拉");
        order.setTotalPrice(12345L);
        order.setStatus(1);
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        order.setCreatedTime(new Date());
        order.setCreatedUser("amy");
        order.setModifiedTime(new Date());
        order.setModifiedUser("amy");

        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();

        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setTitle("广博(GuangBo)16K115页线圈记事本子日记本文具笔记本图案随机");
        orderItem.setImage("images/天猫超市-right-3.jpg");
        orderItem.setPrice(13L);
        orderItem.setNum(1);
        orderItem.setCreatedUser("amy");
        orderItem.setCreatedTime(new Date());
        orderItem.setModifiedUser("amy");
        orderItem.setModifiedTime(new Date());

        orderMapper.insertOrderItem(orderItem);
    }
}
