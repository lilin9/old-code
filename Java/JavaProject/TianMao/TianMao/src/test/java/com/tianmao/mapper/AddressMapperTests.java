package com.tianmao.mapper;

import com.tianmao.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/19/11:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    AddressMapper addressMapper;

    @Test
    public void selectAllAddress() {
        List<Address> list = addressMapper.selectAllAddress(8);
        list.forEach(System.out :: println);
    }

    @Test
    public void selectAddressByAid() {
        System.out.println(addressMapper.selectAddressByAid(1));
    }

    @Test
    public void selectAddressCountByUid() {
        System.out.println(addressMapper.selectAddressCountByUid(8));
    }

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(8);
        address.setName("景天");
        address.setProvinceName("四川省");
        address.setProvinceCode("3001");
        address.setCityName("长安城");
        address.setAreaName("永安当");

        addressMapper.insert(address);
    }

    @Test
    public void updateAllAddressNoDefault() {
        System.out.println(addressMapper.updateAllAddressNoDefault(8));
    }

    @Test
    public void updateAddressDefault() {
        addressMapper.updateAddressDefault(3, "amy", new Date());
    }

    @Test
    public void selectLastModifiedAddress() {
        System.out.println(addressMapper.selectLastModifiedAddress(8));
    }

    @Test
    public void deleteAddressByAid() {
        addressMapper.deleteAddressByAid(1);
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setAid(9);
        address.setName("tony");
        address.setModifiedUser("amy");
        address.setModifiedTime(new Date());
        addressMapper.updateAddress(address);
    }
}
