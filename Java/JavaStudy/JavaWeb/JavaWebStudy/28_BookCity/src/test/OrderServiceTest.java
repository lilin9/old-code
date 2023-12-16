package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/01/16/15:21
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }
}