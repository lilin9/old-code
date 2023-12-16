package dao;

import pojo.OrderItem;

/**
 * Created by MrLi on 2022/01/16/13:43
 * <p>
 * 订单项
 */
public interface OrderItemDao {
    //保存订单项
    int saveOrderItem(OrderItem orderItem);

    //根据订单号查询订单详细
    OrderItem queryOrderItemByOrderId(String orderId);
}
