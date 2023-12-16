package com.tianmao.service.impl;

import com.tianmao.mapper.CartMapper;
import com.tianmao.mapper.ProductMapper;
import com.tianmao.mapper.UserMapper;
import com.tianmao.pojo.Cart;
import com.tianmao.service.CartService;
import com.tianmao.service.ProductSerice;
import com.tianmao.service.exception.*;
import com.tianmao.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/05/12/17:09
 *
 * 购物车的service接口类的实现类
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    /**
     * @Author LiLin
     * @Date 2022/5/12 17:05
     * @Param uid 用户id
     * @return 返回查询到的商品信息组成的vo对象列表
     * @Description 根据uid查询购物车商品信息列表
     */
    @Override
    public List<CartVo> selectCartVoByUid(Integer uid) {
        return cartMapper.selectCartVoByUid(uid);
    }

    /**
     * @Author LiLin
     * @Date 2022/5/12 20:32
     * @Param cid 购物车id
     * @Param num 修改的数量
     * @return 返回影响到的行数
     * @Description 根据cid更新购物车商品数量
     */
    @Override
    public Integer updateCartNum(Integer uid, Integer cid, Integer num, String username) {
        //查询商品数据是否存在
        Cart result = cartMapper.selectCartByCid(cid);
        if (result == null)
            //如果商品数据不存在就抛出以下异常
            throw new CartNotFoundException("购物车不存在");

        //判断用户是否有权限查询购物车
        if (!Objects.equals(uid, result.getUid()))
            //如果用户没有权限，抛出异常
            throw new AccessDeniedException("用户无权限访问数据");

        //更新购物车数量
        Integer rows = cartMapper.updateCartNumByCid(cid, num, username, new Date());
        //如果更新失败
        if (rows != 1)
            //抛出异常
            throw new UpdateException("更新购物车数据失败");
        return rows;
    }

    /**
     * @Author LiLin
     * @Date 2022/5/13 15:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Description 根据购物车id删除购物车商品数据
     */
    @Override
    public void deleteCartByCid(Integer uid, Integer cid) {
        //查询商品数据是否存在
        if (cartMapper.selectCartByCid(cid) == null)
            //不存在，抛异常
            throw new CartNotFoundException("购物车商品数据不存在");

        //删除购物车商品数据
        Integer rows = cartMapper.deleteCartByCid(cid);
        if (rows != 1)
            //如果删除失败，抛出异常
            throw new DeleteException("删除购物车数据出现未知异常");
    }

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
    @Override
    public void insert(Integer uid, Integer pid, String username, Integer num, Long price) {
        //首先确保用户是存在的
        if (userMapper.selectUserByUid(uid) == null)
            throw new UserNotFoundException("用户信息不存在");
        //然后确保商品信息是存在的
        if (productMapper.queryById(pid) == null)
            throw new ProductNotFoundException("商品信息不存在");
        //然后封装一个购物车实体类，完善购物车信息
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setPid(pid);
        cart.setPrice(price);
        cart.setNum(num);
        Date date = new Date();
        cart.setCreatedTime(date);
        cart.setModifiedTime(date);
        cart.setCreatedUser(username);
        cart.setModifiedUser(username);
        //将商品加入购物车
        Integer rows = cartMapper.insert(cart);
        //确保加入成功
        if (rows != 1)
            throw new InsertException("将商品加入购物车出现未知异常");
    }
}
