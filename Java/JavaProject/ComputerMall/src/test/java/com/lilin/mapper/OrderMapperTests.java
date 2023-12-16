package com.lilin.mapper;

import com.lilin.pojo.Order;
import com.lilin.pojo.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiLin on 2022/04/15/13:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void insertToOrder() {
        Order order = new Order();
        order.setUid(1);
        order.setRecvName("admin");
        order.setRecvPhone("18276361404");

        System.out.println(orderMapper.insertToOrder(order));
    }

    @Test
    public void insertToOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("this is a title");
        orderItem.setImage("/i/am/god/i/love/myself/");
        orderItem.setPrice(1234L);
        orderItem.setNum(1);

        System.out.println(orderMapper.insertToOrderItem(orderItem));
    }
}
