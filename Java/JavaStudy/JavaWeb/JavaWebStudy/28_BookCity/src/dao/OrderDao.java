package dao;

import pojo.Order;

import java.util.List;

/**
 * Created by MrLi on 2022/01/16/13:37
 *
 * 订单
 */
public interface OrderDao {
    //保存订单
    int saveOrder(Order order);

    //查询全部订单
    List<Order> queryOrders();

    //修改订单状态
    int changeOrderStatus(String orderId, int status);

    //根据用户编号查询订单信息
    Order queryOrderByUserId(Integer userId);
}
