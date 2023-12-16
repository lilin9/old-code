package com.lilin.springsecurity.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by LiLin on 2022/7/7/12:08:54
 */
@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectUserByUid() {
        System.out.println(userMapper.selectById(8));
    }
}
