package com.tianmao.service;

import com.tianmao.pojo.Address;

import java.util.List;

/**
 * Created by LiLin on 2022/05/19/11:12
 *
 * 收货地址
 */
public interface AddressService {
    /**
     * @Author LiLin
     * @Date 2022/5/19 11:13
     * @Param uid 用户id
     * @return 返回查询到的收货地址列表
     * @Description 根据uid查询所有的收货地址信息
     */
    List<Address> selectAllAddress(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
    Address selectAddressByAid(Integer aid, Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/23 21:39
     * @Param uid 用户id
     * @Param username 用户名
     * @Param address 收货地址实体类
     * @Description 添加收货地址
     */
    void insert(Integer uid, String username, Address address);

    /**
     * @Author LiLin
     * @Date 2022/5/24 13:31
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 修改收货地址为默认收货地址
     */
    void updateDefaultAddress(Integer uid, Integer aid, String username);

    /**
     * @Author LiLin
     * @Date 2022/5/24 14:44
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除收货地址
     */
    void deleteAddress(Integer uid, Integer aid, String username);

    /**
     * @Author LiLin
     * @Date 2022/5/24 16:43
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Param address 收货地址实体类
     * @Description 修改收货地址
     */
    void updateAddress(Integer aid, String username, Address address);
}
