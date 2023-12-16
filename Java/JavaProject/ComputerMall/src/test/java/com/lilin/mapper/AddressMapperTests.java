package com.lilin.mapper;

import com.lilin.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/09/19:04
 */
@SpringBootTest
//@RunWith(SpringRunner.class)：表示启动这个单元测试类（如果没有这个注解，单元测试类就不能启动）
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setName("admin");
        address.setPhone("11111111111");
        addressMapper.insert(address);
    }

    @Test
    public void countAddressByUid() {
        System.out.println(addressMapper.countAddressByUid(1));
    }

    @Test
    public void selectAddressByUid() {
        List<Address> list = addressMapper.selectAddressByUid(6);

        list.forEach(System.out :: println);
    }

    @Test
    public void selectDefaultAddress() {
        System.out.println(addressMapper.selectDefaultAddressByAid(4));
    }

    @Test
    public void updateAllAddressNonDefault() {
        System.out.println("影响的行数：" + addressMapper.updateAllAddressNonDefault(6));
    }

    @Test
    public void updateAddressDefault() {
        System.out.println("影响的行数：" + addressMapper.updateAddressDefault("admin", new Date(), 4));
    }

    @Test
    public void deleteAddressByAid() {
        System.out.println(addressMapper.deleteAddressByAid(5));
    }

    @Test
    public void selectLastModifiedAddress() {
        System.out.println(addressMapper.selectLastModifiedAddress(6));
    }
}
