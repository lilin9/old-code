package com.tianmao.mapper;

import com.tianmao.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by LiLin on 2022/04/08/20:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123abc");
        user.setPhone("12345678901");

        userMapper.insert(user);
    }

    @Test
    public void selectUserByUsername() {
        System.out.println(userMapper.selectUserByUsername("any"));
    }

    @Test
    public void selectUserByUid() {
        System.out.println(userMapper.selectUserByUid(8));
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(7, "abc123", "admin", new Date());
    }

    @Test
    public void updateUserInfo() {
        User user = new User();
        user.setUid(17);
        user.setUsername("morty");
        user.setPhone("12345678901");
        user.setEmail("morty@qq.com");
        user.setGender(1);
        userMapper.updateUserInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid() {
        userMapper.updateAvatarByUid(8, "images/avatar/amy.jpg", "amy", new Date());
    }
}
