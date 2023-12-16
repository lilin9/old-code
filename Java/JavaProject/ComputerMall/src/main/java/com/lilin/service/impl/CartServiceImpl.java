package com.lilin.service.impl;

import com.lilin.mapper.CartMapper;
import com.lilin.mapper.ProductMapper;
import com.lilin.pojo.Cart;
import com.lilin.service.CartService;
import com.lilin.service.exception.AccessDeniedException;
import com.lilin.service.exception.CartNotFoundException;
import com.lilin.service.exception.InsertException;
import com.lilin.service.exception.UpdateException;
import com.lilin.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/04/15/14:18
 *
 * 购物车业务层接口的实现类
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * @Author LiLin
     * @Date 2022/4/15 14:19
     * @Param uid 用户id
     * @Param cid 购物车id
     * @Param amount 商品数量
     * @Param username 修改人用户名
     * @Description 添加购物车数据
     */
    @Override
    public void insert(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.selectCartByUidAndPid(uid, pid);
        Date date = new Date();
        if (result == null) { //如果购物车没有这个商品的数据，进行添加操作
            Cart cart = new Cart();
            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //添加价格，价格从商品中获取
            cart.setPrice(productMapper.selectProductById(pid).getPrice());

            //往购物车中添加商品数据
            if (cartMapper.insert(cart) != 1)
                throw new InsertException("添加商品数据时产生未知异常");
        } else { //如果购物车有了商品的数据，则进行更新操作
            //获取商品总数
            Integer num = result.getNum() + amount;
            //更新商品数量
            Integer rows = cartMapper.updateCartNum(result.getCid(), num, username, date);
            //如果更新失败
            if (rows != 1)
                throw new UpdateException("更新商品数量时出现未知异常");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
    @Override
    public List<CartVo> selectVOByUid(Integer uid) {
        return cartMapper.selectVOByUid(uid);
    }

    /**
     * @Author LiLin
     * @Date 2022/4/16 10:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Param username 更改人姓名
     * @return 返回更新后的购物车数量
     * @Description 增加购物车中的商品数量
     */
    @Override
    public Integer addCartNum(Integer cid, Integer uid, String username) {
        //查询购物车数据是否存在
        Cart result = cartMapper.selectCartByCid(cid);
        if (result == null )
            throw new CartNotFoundException("数据不存在");

        //检查用户合法性
        if (!uid.equals(result.getUid()))
            throw new AccessDeniedException("数据非法访问");

        //更新数量
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateCartNum(cid, num, username, new Date());

        //如果更新失败
        if (rows != 1)
            throw new UpdateException("更新数据过程中出现未知异常");

        return num;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cidList 购物车id列表
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
    @Override
    public List<CartVo> selectVOByCidList(Integer uid, Integer[] cidList) {
        List<CartVo> list = cartMapper.selectVOByCidList(cidList);
        //如果 cidList 集合中有不属于 uid 的数据，就将其移除
        list.removeIf(cartVo -> !Objects.equals(cartVo.getUid(), uid));
        return list;
    }
}
