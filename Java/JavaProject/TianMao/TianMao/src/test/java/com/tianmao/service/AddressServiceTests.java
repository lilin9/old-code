package com.tianmao.service;

import com.tianmao.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/19/11:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    AddressService addressService;

    @Test
    public void selectAllAddress() {
        List<Address> list = addressService.selectAllAddress(8);
        list.forEach(System.out :: println);
    }

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(8);
        address.setName("茂茂");
        address.setProvinceName("四川省");
        address.setProvinceCode("3001");
        address.setCityName("长安城");
        address.setAreaName("永安当");

        addressService.insert(8, "amy", address);
    }

    @Test
    public void updateDefaultAddress() {
        addressService.updateDefaultAddress(8, 5, "amy");
    }

    @Test
    public void deleteAddress() {
        addressService.deleteAddress(8, 2, "amy");
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setName("tom");
        addressService.updateAddress(9, "admin", address);
    }
}
