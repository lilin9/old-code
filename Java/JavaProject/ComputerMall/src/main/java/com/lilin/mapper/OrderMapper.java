package com.lilin.mapper;

import com.lilin.pojo.Order;
import com.lilin.pojo.OrderItem;

/**
 * Created by LiLin on 2022/04/19/15:13
 *
 * 订单模块持久层mapper方法
 */
public interface OrderMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/19 15:16
     * @Param order 订单表的实体类
     * @return 返回影响的行数
     * @Description 向Order表中添加数据
     */
    Integer insertToOrder(Order order);

    /**
     * @Author LiLin
     * @Date 2022/4/19 15:16
     * @Param orderItem 订单项表的实体类
     * @return 返回影响的行数
     * @Description 向OrderItem表中添加数据
     */
    Integer insertToOrderItem(OrderItem orderItem);
}
