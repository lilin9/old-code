package com.tianmao.service;

import com.tianmao.pojo.Order;
import com.tianmao.vo.CartVo;

import java.util.List;

/**
 * Created by LiLin on 2022/05/16/11:46
 *
 * 订单的service接口类
 */
public interface OrderService {
    /**
     * @Author LiLin
     * @Date 2022/5/16 11:28
     * @Param pids 商品id的集合
     * @Param uid 用户id
     * @return 返回查询到的 CartVo 列表
     * @Description 根据cids查询购物车商品数据
     */
    List<CartVo> selectCartVoByCids(Integer uid, List<Integer> pids);

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
