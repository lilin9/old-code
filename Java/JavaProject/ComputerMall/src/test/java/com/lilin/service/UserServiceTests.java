package com.lilin.service;

import com.lilin.pojo.User;
import com.lilin.service.exception.father.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiLin on 2022/04/05/14:36
 *
 * 用户业务层单元测试方法
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("smith");
            user.setPassword("123abc");
            userService.register(user);
            System.out.println("执行完毕");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + " ---> " + e.getMessage());
        }
    }

    @Test
    public void login() {
        System.out.println(userService.login("amy", "123456"));
    }

    @Test
    public void updatePasswordByUid() {
        //FABC4444121B4A1A3E17096428FF6BE4
        //24BA998850D43389B4FD5B607A500504
        userService.updatePasswordByUid(6, "amy", "123abc", "123456");
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setPhone("11111222223");
        user.setEmail("smith@qq.com");
        user.setGender(1);

        userService.updateInfo(3, "smith", user);
    }

    @Test
    public void updateAvatarByUid() {
        userService.updateAvatar(1, "/images/avatar/1.jpg", "admin");
    }
}
