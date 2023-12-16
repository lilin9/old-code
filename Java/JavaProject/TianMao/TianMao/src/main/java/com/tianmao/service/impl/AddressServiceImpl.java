package com.tianmao.service.impl;

import com.tianmao.mapper.AddressMapper;
import com.tianmao.pojo.Address;
import com.tianmao.service.AddressService;
import com.tianmao.service.DictDistrictService;
import com.tianmao.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/19/11:14
 * <p>
 * 收货地址实现类
 */
@Service
public class AddressServiceImpl implements AddressService {
    //用户最多地址数
    //最大个数从配置文件 application.properties 中读取
    @Value("${user.address.max-count}")
    private Integer MAX_COUNT;

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DictDistrictService dictDistrictService;

    /**
     * @return 返回查询到的收货地址列表
     * @Author LiLin
     * @Date 2022/5/19 11:13
     * @Param uid 用户id
     * @Description 根据uid查询所有的收货地址信息
     */
    @Override
    public List<Address> selectAllAddress(Integer uid) {
        List<Address> list = addressMapper.selectAllAddress(uid);
        List<Address> temp = new ArrayList<>();

        for (Address item : list) {
            Address address = new Address();
            address.setAid(item.getAid());
            address.setTag(item.getTag());
            address.setName(item.getName());
            address.setAddress(item.getAddress());
            address.setPhone(item.getPhone());
            temp.add(address);
        }

        return temp;
    }

    /**
     * @return 返回查询到的收货地址数据
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @Description 根据aid获取收货地址数据
     */
    @Override
    public Address selectAddressByAid(Integer aid, Integer uid) {
        Address address = addressMapper.selectAddressByAid(aid);

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

    /**
     * @Author LiLin
     * @Date 2022/5/23 21:39
     * @Param uid 用户id
     * @Param username 用户名
     * @Param address 收货地址实体类
     * @Description 添加收货地址
     */
    @Override
    public void insert(Integer uid, String username, Address address) {
        //判断用户收货地址数是否超出最大个数限制
        Integer count = addressMapper.selectAddressCountByUid(uid);
        //超出了抛出异常
        if (count >= MAX_COUNT) throw new AddressCountLimitException("用户收货地址个数超出最大限制");

        //补全用户信息
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);   //1 标识默认，0 标识不默认
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);
        //补全收货地址的省市区信息
        address.setProvinceName(dictDistrictService.selectNameByCode(address.getProvinceCode()));
        address.setCityName(dictDistrictService.selectNameByCode(address.getCityCode()));
        address.setAreaName(dictDistrictService.selectNameByCode(address.getAreaCode()));

        //插入收货地址
        Integer rows = addressMapper.insert(address);
        //如果插入失败，抛出异常
        if (rows != 1) throw new InsertException("保存用户地址时出现未知错误");
    }

    /**
     * @Author LiLin
     * @Date 2022/5/24 13:31
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param modifiedUser 用户名
     * @Description 修改收货地址为默认收货地址
     */
    @Override
    public void updateDefaultAddress(Integer uid, Integer aid, String username) {
        //查询用户默认地址信息是否存在
        Address address = addressMapper.selectAddressByAid(aid);
        // 如果不存在抛出异常
        if (address == null)
            throw new AddressNotFoundException("用户收货地址不存在");

        //检查当前获取的收货地址归属
        if (!address.getAid().equals(aid))
            throw new AccessDeniedException("数据非法访问");

        //将用户所有收货地址都设置为非默认地址
        Integer result = addressMapper.updateAllAddressNoDefault(uid);
        //如果修改失败，抛出异常
        if (result == null || result < 1)
            throw new UpdateException("修改用户默认收货地址过程中出现未知异常");

        //修改用户默认收货地址
        result = addressMapper.updateAddressDefault(aid, username, new Date());
        //如果修改失败，抛出异常
        if (result == null || result != 1)
            throw new UpdateException("修改用户默认收货地址过程中出现未知异常");
    }

    /**
     * @Author LiLin
     * @Date 2022/5/24 14:44
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除收货地址
     */
    @Override
    public void deleteAddress(Integer uid, Integer aid, String username) {
        Address result = addressMapper.selectAddressByAid(aid);
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
        if (addressMapper.selectAddressCountByUid(uid) != 0 && result.getIsDefault() == 1) {
            //查询最新修改的数据
            Address address = addressMapper.selectLastModifiedAddress(uid);
            //将最新的数据设为默认收货地址
            Integer rows = addressMapper.updateAddressDefault(aid, username, new Date());

            //如果修改数据失败
            if (rows != 1)
                throw new UpdateException("修改收货地址信息时出现未知异常");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/5/24 16:43
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Param address 收货地址实体类
     * @Description 修改收货地址
     */
    @Override
    public void updateAddress(Integer aid, String username, Address address) {
        //判断收货地址是否存在
        if (addressMapper.selectAddressByAid(aid) == null)
            throw new AddressNotFoundException("收货地址不存在");

        //补全收货地址信息
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setAid(aid);

        //更新收货地址
        Integer rows = addressMapper.updateAddress(address);
        //如果更新失败，抛出异常
        if (rows != 1)
            throw new UpdateException("更新收货地址出现未知异常");
    }
}
