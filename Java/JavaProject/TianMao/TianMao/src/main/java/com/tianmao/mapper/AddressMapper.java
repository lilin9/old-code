package com.tianmao.mapper;

import com.tianmao.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/19/10:47
 *
 * 收货地址
 */
@Mapper
public interface AddressMapper {
    /**
     * @Author LiLin
     * @Date 2022/5/19 10:57
     * @Param uid 用户id
     * @return 返回查询到的收货地址列表
     * @Description 根据uid查询所有的收货地址
     */
    List<Address> selectAllAddress(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
    Address selectAddressByAid(Integer aid);

    /**
     * @Author LiLin
     * @Date 2022/5/23 21:22
     * @Param uid 用户id
     * @return 返回查询到的收货地址数量
     * @Description 查询用户的收货地址数量，根据用户id查询
     */
    Integer selectAddressCountByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/23 21:23
     * @Param address 收货地址实体类
     * @return 返回影响的行数
     * @Description 往数据表插入收货地址
     */
    Integer insert(Address address);

    /**
     * @Author LiLin
     * @Date 2022/5/24 12:51
     * @Param uid 用户id
     * @return 返回影响的行数
     * @Description 修改用户所有的收货地址为非默认收货地址
     */
    Integer updateAllAddressNoDefault(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/24 12:54
     * @Param aid 收货地址id
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @return 返回影响的行数
     * @Description 修改收货地址为默认收货地址
     */
    Integer updateAddressDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/5/24 14:37
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 查询用户最后一次修改的收货地址数据
     */
    Address selectLastModifiedAddress(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/24 14:38
     * @Param aid 收货地址id
     * @return 返回影响的行数
     * @Description 根据aid删除收获电子hi
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * @Author LiLin
     * @Date 2022/5/24 16:30
     * @Param address 收货地址实体类
     * @return 返回影响的行数
     * @Description 修改收货地址信息
     */
    Integer updateAddress(Address address);
}
