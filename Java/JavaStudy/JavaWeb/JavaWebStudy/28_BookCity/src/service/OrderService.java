package service;

import pojo.Cart;

/**
 * Created by MrLi on 2022/01/16/14:59
 */
public interface OrderService {
    // 生成订单
    String createOrder(Cart cart, Integer userId);

/*    // 查询全部订单
    showAllOders()

    // 发货状态：发货/未发
    sendOrder(oderId)

    // 查看订单详情
    showOrderDetail(orderId)

    // 查看我的订单
    showMyOrders(userId)

    // 签收订单/确认收货
    receiverOrder(orderId)*/
}
