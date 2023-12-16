package com.lilin.service.impl;

import com.lilin.mapper.OrderMapper;
import com.lilin.pojo.Address;
import com.lilin.pojo.Order;
import com.lilin.pojo.OrderItem;
import com.lilin.service.AddressService;
import com.lilin.service.CartService;
import com.lilin.service.OrderService;
import com.lilin.service.exception.InsertException;
import com.lilin.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/19/17:00
 *
 * 订单的业务层接口的实现类
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;

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
    @Override
    public Order createOrder(Integer aid, Integer uid, Integer[] cids, String username) {
        //获取购物车数据
        List<CartVo> list = cartService.selectVOByCidList(uid, cids);

        //计算商品的总价
        long totalPrice = 0L;
        for (CartVo cartVo: list)
            totalPrice = cartVo.getRealPrice() * cartVo.getNum();

        //获取收货地址数据
        Address address = addressService.getAddressByAid(aid, uid);


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
        //添加日志
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(date);
        order.setModifiedTime(date);

        //保存订单信息
        if (orderMapper.insertToOrder(order) != 1)
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
            if (orderMapper.insertToOrderItem(orderItem) != 1)
                throw new InsertException("保存订单详细项数据过程中出现未知异常");
        }

        return order;
    }
}
