package com.MrLi.demo;

import com.MrLi.demo.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class SpringBoot4ApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        //select * from t_book where id=1
        Book book = jdbcTemplate.queryForObject("select * from t_book where id=1", Book.class);
        System.out.println(book);
    }

    @Test
    void testRedis() {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("hello", "redis");
        System.out.println(opsForValue.get("hello"));
        System.out.println(redisConnectionFactory.getClass());
    }
}
