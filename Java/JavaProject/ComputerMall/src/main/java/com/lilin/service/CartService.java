package com.lilin.service;


import com.lilin.vo.CartVo;

import java.util.List;

/**
 * Created by LiLin on 2022/04/15/14:17
 *
 * 购物车业务层接口
 */
public interface CartService {
    /**
     * @Author LiLin
     * @Date 2022/4/15 14:19
     * @Param uid 用户id
     * @Param cid 购物车id
     * @Param amount 商品数量
     * @Param username 修改人用户名
     * @Description 添加购物车数据
     */
    void insert(Integer uid, Integer pid, Integer amount, String username);

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
     * @Date 2022/4/16 10:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Param username 更改人姓名
     * @return 返回更新后的购物车数量
     * @Description 增加购物车中的商品数量
     */
    Integer addCartNum(Integer cid, Integer uid, String username);

    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cidList 购物车id列表
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
    List<CartVo> selectVOByCidList(Integer uid, Integer[] cidList);
}
