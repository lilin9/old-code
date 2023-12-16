package com.lilin.mapper;

import com.lilin.pojo.Cart;
import com.lilin.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/15/13:25
 *
 * 购物测持久层mapper方法
 */
public interface CartMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/15 13:27
     * @Param cart 购物车实体类
     * @return 返回插入的行数
     * @Description 插入购物车数据
     */
    Integer insert(Cart cart);

    /**
     * @Author LiLin
     * @Date 2022/4/15 13:29
     * @Param cid 购物车id
     * @return 返回查询到的购物车信息
     * @Description 查询购物车信息
     */
    Cart selectCartByUidAndPid(Integer uid, Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/4/15 13:30
     * @Param cid 购物车id
     * @Param uid 用户id
     * @return 返回更新的行数
     * @Description 更新购物车中商品数量
     */
    Integer updateCartNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
    List<CartVo> selectVOByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/16 10:39
     * @Param cid 购物车id
     * @return 返回查询到的购物车数据
     * @Description 查询购物车中的商品信息
     */
    Cart selectCartByCid(Integer cid);

    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cidList 购物车id列表
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
    List<CartVo> selectVOByCidList(Integer[] cidList);
}
