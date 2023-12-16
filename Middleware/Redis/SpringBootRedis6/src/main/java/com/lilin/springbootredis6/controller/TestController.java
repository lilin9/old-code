package com.lilin.springbootredis6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/7/29/12:51:27
 */
@RestController
@RequestMapping("redisTest")
public class TestController {
    @Autowired
    RedisTemplate<String, Object> template;

    @RequestMapping("test")
    public String test() {
        //添加值到 redis 中
        template.opsForValue().set("name", "tom");
        //从 redis 中获取值
        return (String) template.opsForValue().get("name");
    }
}
