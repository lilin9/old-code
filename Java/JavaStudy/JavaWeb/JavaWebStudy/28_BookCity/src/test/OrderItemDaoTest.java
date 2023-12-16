package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/01/16/14:47
 */
public class OrderItemDaoTest {
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "百年孤独", 1, new BigDecimal(30), new BigDecimal(30), "001"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        OrderItem orderItem = orderItemDao.queryOrderItemByOrderId("001");
        System.out.println(orderItem);
    }
}