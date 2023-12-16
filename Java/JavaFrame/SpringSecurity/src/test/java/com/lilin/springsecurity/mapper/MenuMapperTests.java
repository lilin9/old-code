package com.lilin.springsecurity.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by LiLin on 2022/7/9/17:21:43
 */
@SpringBootTest
public class MenuMapperTests {
    @Autowired
    MenuMapper menuMapper;

    @Test
    public void selectPermsByUserId() {
        menuMapper.selectPermsByUserId(1L).forEach(System.out :: println);
    }
}
