package com.lilin.mapper;

import com.lilin.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LiLin on 2022/04/05/11:14
 *
 * 用户的mapper单元测试方法
 */
@SpringBootTest
//@RunWith(SpringRunner.class)：表示启动这个单元测试类（如果没有这个注解，单元测试类就不能启动）
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123abc");
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void selectUserByUsername() {
        User user = userMapper.selectUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void updatePassword() {
        userMapper.updatePasswordByUid(1, "123456", "admin", new Date());
    }

    @Test
    public void selectUserByUid() {
        System.out.println(userMapper.selectUserByUid(1));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(6);
        user.setPhone("18593237840");
        user.setEmail("amy@qq.com");
        user.setGender(0);
        user.setModifiedUser("admin");
        user.setModifiedTime(new Date());

        Integer result = userMapper.updateInfoByUid(user);
        System.out.println("影响力" + result + "行");
    }

    @Test
    public void updateAvatarByUid() {
        System.out.println(userMapper.updateAvatarByUid(6, "/images/avatar/1.jpg", "amy", new Date()));
    }

    @Test
    public void test() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
        String now = format.format(date);

        System.out.println(date);
        System.out.println(now);
    }
}
