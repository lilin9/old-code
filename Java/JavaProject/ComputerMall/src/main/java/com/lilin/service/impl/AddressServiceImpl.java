package com.lilin.service.impl;

import com.lilin.mapper.AddressMapper;
import com.lilin.pojo.Address;
import com.lilin.service.AddressService;
import com.lilin.service.DistrictService;
import com.lilin.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/12/10:31
 *
 * 收货地址业务层接口的实现类
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    //在添加用户的收货地址的业务层时，会依赖于 DistrictService 的业务层接口
    @Autowired
    DistrictService districtService;

    //用户最多地址数
    //最大个数从配置文件 application.properties 中读取
    @Value("${user.address.max-count}")
    private Integer MAX_COUNT;

    /**
     * @Author LiLin
     * @Date 2022/4/12 10:36
     * @Param uid 用户的id
     * @Param username 用户名
     * @Param address 收货地址
     * @Description 添加新的收货地址
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //判断用户收货地址数是否超出最大个数限制
        Integer count = addressMapper.countAddressByUid(uid);
        //超出了抛出异常
        if (count >= MAX_COUNT) throw new AddressCountLimitException("用户收货地址个数超出最大限制");

        //获取省市区的名称
        String provinceName = districtService.selectNameByCode(address.getProvinceCode());
        String cityName = districtService.selectNameByCode(address.getCityCode());
        String areaName = districtService.selectNameByCode(address.getAreaCode());

        //补全用户信息
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);   //1 标识默认，0 标识不默认
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //插入收货地址
        Integer rows = addressMapper.insert(address);
        //如果插入失败，抛出异常
        if (rows != 1) throw new InsertException("保存用户地址时出现未知错误");
    }


    /**
     * @Author LiLin
     * @Date 2022/4/13 13:09
     * @Param uid 用户id
     * @return 返回用户地址信息的列表
     * @Description 根据uid获取用户的地址信息
     */
    @Override
    public List<Address> getAddressByUid(Integer uid) {
        List<Address> list = addressMapper.selectAddressByUid(uid);
        List<Address> temporary = new ArrayList<>();

        //补全数据
        for (Address item : list) {
            Address address = new Address();

            address.setAid(item.getAid());
            address.setUid(item.getUid());
            address.setTag(item.getTag());
            address.setName(item.getName());
            address.setAddress(item.getAddress());
            address.setPhone(item.getPhone());
            address.setProvinceName(item.getProvinceName());
            address.setCityName(item.getCityName());
            address.setAreaName(item.getAreaName());

            temporary.add(address);
        }

        return temporary;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/13 15:57
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @Description 更改用户的收货地址为默认地址
     */
    @Override
    public void setDefaultAddress(Integer uid, Integer aid, String modifiedUser, Date modifiedTime) {
        //查询用户默认地址信息是否存在
        Address address = addressMapper.selectDefaultAddressByAid(aid);
        // 如果不存在抛出异常
        if (addressMapper.selectDefaultAddressByAid(aid) == null)
            throw new AddressNotFoundException("用户收货地址不存在");

        //检查当前获取的收货地址归属
        if (!address.getUid().equals(uid))
            throw new AccessDeniedException("数据非法访问");

        //将用户所有收货地址都设置为非默认地址
        Integer result = addressMapper.updateAllAddressNonDefault(uid);
        //如果修改失败，抛出异常
        if (result == null || result < 1)
            throw new UpdateException("修改用户默认收货地址过程中出现未知异常");

        //修改用户默认收货地址
        result = addressMapper.updateAddressDefault(modifiedUser, modifiedTime, aid);
        //如果修改失败，抛出异常
        if (result == null || result != 1)
            throw new UpdateException("修改用户默认收货地址过程中出现未知异常");
    }

    /**
     * @Author LiLin
     * @Date 2022/4/14 8:50
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除用户的收货地址信息
     */
    @Override
    public void deleteAddress(Integer uid, Integer aid, String username) {
        Address result = addressMapper.selectDefaultAddressByAid(aid);
        //判断要删除的收货地址是否存在
        if (result == null)
            throw new AddressNotFoundException("收货地址不存在");

        //检查当前获取的收货地址归属
        if (!result.getUid().equals(uid))
            throw new AccessDeniedException("数据非法访问");

        //删除收货地址
        //如果删除失败
        if (addressMapper.deleteAddressByAid(aid) != 1)
            throw new DeleteException("删除收货地址过程中出现未知异常");

        //查询收货地址剩余数量
        //如果还有地址，以及删除的收货地址是默认地址
        if (addressMapper.countAddressByUid(uid) != 0 && result.getIsDefault() == 1) {
            //查询最新修改的数据
            Address address = addressMapper.selectLastModifiedAddress(uid);
            //将最新的数据设为默认收货地址
            Integer rows = addressMapper.updateAddressDefault(username, new Date(), address.getAid());

            //如果修改数据失败
            if (rows != 1)
                throw new UpdateException("修改收货地址信息时出现未知异常");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
    @Override
    public Address getAddressByAid(Integer aid, Integer uid) {
        Address address = addressMapper.selectDefaultAddressByAid(aid);

        //异常判断
        if (address == null)
            throw new AddressNotFoundException("收货地址数据不存在");
        if (!uid.equals(address.getUid()))
            throw new AccessDeniedException("收货地址数据非法访问");

        //减少数据冗余
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);

        return address;
    }
}
