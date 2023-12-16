package com.lilin.service;

import com.lilin.pojo.Address;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/12/10:31
 *
 * 收货地址业务层接口
 */
public interface AddressService {
    /**
     * @Author LiLin
     * @Date 2022/4/12 10:36
     * @Param uid 用户的id
     * @Param username 用户名
     * @Param address 收货地址
     * @Description 添加新的收货地址
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * @Author LiLin
     * @Date 2022/4/13 13:09
     * @Param uid 用户id
     * @return 返回用户地址信息的列表
     * @Description 根据uid获取用户的地址信息
     */
    List<Address> getAddressByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/13 15:57
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @Description 更改用户的收货地址为默认地址
     */
    void setDefaultAddress(Integer uid, Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/4/14 8:50
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除用户的收货地址信息
     */
    void deleteAddress(Integer uid, Integer aid, String username);

    /**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
    Address getAddressByAid(Integer aid, Integer uid);
}
