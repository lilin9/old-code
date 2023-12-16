package dao.impl;

import dao.BaseDao;
import dao.OrderItemDao;
import pojo.OrderItem;

/**
 * Created by MrLi on 2022/01/16/13:45
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public OrderItem queryOrderItemByOrderId(String orderId) {
        String sql = "select `name`,`count`,`price`,`total_price`,`order_id` from t_order_item where `order_id`=?";
        return queryForOne(OrderItem.class, sql, orderId);
    }
}
