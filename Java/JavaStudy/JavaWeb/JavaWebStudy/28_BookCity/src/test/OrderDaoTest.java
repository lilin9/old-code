package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MrLi on 2022/01/16/14:36
 */
public class OrderDaoTest {

    private final OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("001", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("001", 1);
    }

    @Test
    public void queryOrderByUserId() {
        Order order = orderDao.queryOrderByUserId(1);
        System.out.println(order);
    }
}