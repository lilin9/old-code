package com.lilin.mapper;

import com.lilin.pojo.Address;
import com.lilin.pojo.District;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/09/16:54
 *
 * 收货地址持久层接口
 */
public interface AddressMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/9 16:55
     * @Param address 收货地址实体类
     * @return 返回影响的行数
     * @Description 增加新的收货地址
     */
    Integer insert(Address address);

    /**
     * @Author LiLin
     * @Date 2022/4/9 17:00
     * @Param uid 用户的id
     * @return 返回用户的收货地址个数
     * @Description 根据用户的id统计收货地址的个数
     */
    Integer countAddressByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/13 12:50
     * @Param uid 用户id
     * @return 返回用户收货地址的列表
     * @Description 根据uid查询用户收货地址信息
     */
    List<Address> selectAddressByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/13 15:16
     * @Param aid 用户的收货地址id
     * @return 返回查询到的收货地址信息
     * @Description 根据aid，查询用户的默认收货地址
     */
    Address selectDefaultAddressByAid(Integer aid);

    /**
     * @Author LiLin
     * @Date 2022/4/13 15:18
     * @Param uid 用户id
     * @return 返回影响的行数
     * @Description 更改用户的所有收货地址为非默认
     */
    Integer updateAllAddressNonDefault(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/13 15:20
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @Param aid 用户的收货地址id
     * @return 返回影响的行数
     * @Description 更改用户选中的收货地址为默认收货地址
     */
    Integer updateAddressDefault(String modifiedUser, Date modifiedTime, Integer aid);

    /**
     * @Author LiLin
     * @Date 2022/4/14 8:27
     * @Param aid 收货地址id
     * @return 返回影响的行数
     * @Description 根据aid删除用户收货地址
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * @Author LiLin
     * @Date 2022/4/14 8:30
     * @Param uid 用户id
     * @return 返回查询到的收货地址信息
     * @Description 查询用户最后修改的收货地址记录
     */
    Address selectLastModifiedAddress(Integer uid);
}
