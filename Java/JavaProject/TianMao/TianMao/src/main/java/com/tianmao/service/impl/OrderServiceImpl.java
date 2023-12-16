package com.tianmao.service.impl;

import com.tianmao.mapper.OrderMapper;
import com.tianmao.pojo.Address;
import com.tianmao.pojo.Order;
import com.tianmao.pojo.OrderItem;
import com.tianmao.service.AddressService;
import com.tianmao.service.CartService;
import com.tianmao.service.OrderService;
import com.tianmao.service.exception.InsertException;
import com.tianmao.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/05/16/11:48
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartService cartService;
    @Autowired
    AddressService addressService;

    /**
     * @Author LiLin
     * @Date 2022/5/16 11:28
     * @Param pids 商品id的集合
     * @Param uid 用户id
     * @return 返回查询到的 CartVo 列表
     * @Description 根据cids查询购物车商品数据
     */
    @Override
    public List<CartVo> selectCartVoByCids(Integer uid, List<Integer> pids) {
        List<CartVo> list = orderMapper.selectCartVoByCids(pids);
        //如果 list 中有不属于 uid 的数据，就将其移除
        list.removeIf(cartVo -> !Objects.equals(cartVo.getUid(), uid));
        return list;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/19 16:57
     * @Param aid 收货地址id
     * @Param uid 用户的id
     * @Param cids 购物车的id集合
     * @Param username 创建订单的用户名
     * @return 返回创建的订单
     * @Description 创建订单
     */
    @Override
    public Order createOrder(Integer aid, Integer uid, Integer[] cids, String username) {
        //获取购物车数据
        List<CartVo> list = cartService.selectCartVoByUid(uid);

        //计算商品的总价
        long totalPrice = 0L;
        for (CartVo cartVo: list)
            totalPrice = cartVo.getRealPrice() * cartVo.getNum();

        //获取收货地址数据
        Address address = addressService.selectAddressByAid(aid, uid);


        Order order = new Order();
        //补全order数据
        //添加收货地址数据
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityCode());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //添加支付、总价、时间
        Date date = new Date();
        order.setOrderTime(date);
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setPayTime(date);
        //添加日志
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(date);
        order.setModifiedTime(date);

        //保存订单信息
        if (orderMapper.insertOrder(order) != 1)
            throw new InsertException("保存订单数据过程中出现未知异常");

        for (CartVo cartVo: list) {
            //创建订单详细项的数据
            OrderItem orderItem = new OrderItem();

            //补全orderItem数据
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());
            //日志信息
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);
            orderItem.setCreatedTime(date);
            orderItem.setModifiedTime(date);

            //保存订单详细项数据
            if (orderMapper.insertOrderItem(orderItem) != 1)
                throw new InsertException("保存订单详细项数据过程中出现未知异常");
        }

        return order;
    }
}
