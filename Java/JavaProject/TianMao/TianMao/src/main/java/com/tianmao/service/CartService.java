package com.tianmao.service;

import com.tianmao.vo.CartVo;
import java.util.List;

/**
 * Created by LiLin on 2022/05/12/17:03
 *
 * 购物车的service接口类
 */
public interface CartService {

    /**
     * @Author LiLin
     * @Date 2022/5/12 17:05
     * @Param uid 用户id
     * @return 返回查询到的商品信息组成的vo对象列表
     * @Description 根据uid查询购物车商品信息列表
     */
    List<CartVo> selectCartVoByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/12 20:32
     * @Param uid 用户id
     * @Param cid 购物车id
     * @Param username 用户名
     * @return 返回影响到的行数
     * @Description 增加购物车商品数量
     */
    Integer updateCartNum(Integer uid, Integer cid, Integer num, String username);

    /**
     * @Author LiLin
     * @Date 2022/5/13 15:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Description 根据购物车id删除购物车商品数据
     */
    void deleteCartByCid(Integer uid, Integer cid);

    /**
     * @Author lilin
     * @Date 2022/5/30 14:04:15
     * @param uid 用户id
     * @param pid 商品id
     * @param username 用户名
     * @param num 商品数量
     * @param price 商品价格
     * @Description 将商品加入购物车
     */
    void insert(Integer uid, Integer pid, String username, Integer num, Long price);
}
