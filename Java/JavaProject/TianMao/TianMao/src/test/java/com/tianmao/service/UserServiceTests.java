package com.tianmao.service;

import com.tianmao.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by LiLin on 2022/04/10/16:02
 *
 * 用户业务层模块测试类
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    UserService userService;
    @Test
    public void register() {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123abc");
        user.setPhone("12345678901");
        userService.register(user);
    }

    @Test
    public void login() {
        System.out.println(userService.login("any", "123abc"));
    }

    @Test
    public void updatePassword() {
        userService.updatePassword(2, "456def", "123abc");
    }

    @Test
    public void selectUser() {
        System.out.println(userService.selectUser(8));
    }

    @Test
    public void updateUserInfo() {
        User user = new User();
        user.setPhone("18276361404");
        user.setEmail("amy@qq.com");
        user.setGender(0);
        userService.updateUserInfo(8, "amy", user);
    }

    @Test
    public void updateAvatarByUid() {
        userService.updateAvatarByUid(7, "images/avatar/image/amy.jpg", "any");
    }

    @Test
    public void random() {
        for (int i = 0; i < 10; i++) {
            int number = (int) ((Math.random() * 9 + 1) * Math.pow(10, 4 - 1));
            System.out.println(number);
        }
    }
}
