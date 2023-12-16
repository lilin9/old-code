package com.lilin.service;

import com.lilin.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/12/11:44
 *
 * 地址业务层单元测试方法
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    AddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setName("smith");
        address.setPhone("12345678901");
        address.setAddress("神圣兽国游尾郡窝窝乡独行族");

        addressService.addNewAddress(2, "tom", address);
    }

    @Test
    public void getAddressByUid() {
        List<Address> list = addressService.getAddressByUid(1);

        list.forEach(System.out :: println);
    }

    @Test
    public void setDefaultAddress() {
        addressService.setDefaultAddress(6, 3, "youLove", new Date());
    }

    @Test
    public void deleteAddress() {
        addressService.deleteAddress(1, 1, "admin");
    }
}
