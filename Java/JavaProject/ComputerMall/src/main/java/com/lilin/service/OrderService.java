package com.lilin.service;

import com.lilin.pojo.Order;

/**
 * Created by LiLin on 2022/04/19/16:56
 *
 * 订单的业务层接口
 */
public interface OrderService {
    /**
     * @Author LiLin
     * @Date 2022/4/19 16:57
     * @Param aid 收货地址id
     * @Param uid 用户的id
     * @Param cids 购物车的id集合
     * @Param username 用户名
     * @return 返回创建的订单
     * @Description 创建订单
     */
    Order createOrder(Integer aid, Integer uid, Integer[] cids, String username);
}
