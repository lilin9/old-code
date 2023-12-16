package com.lilin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiLin on 2022/9/21/17:04:06
 * 操作 redis 的工具类
 */
@Component
public class RedisConnectionUtils {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /*
    String 类型数据操纵
     */
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * @Author lilin
     * @Date 2022/9/21 17:31:52
     * @param key 键
     * @param value 值
     * @param timeout 过期时间，单位是 ms
     * @Return
     * @Description 往 redis 存入 String 类型的值并设置过期时间
     */
    public void setString(String key, String value, Long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }
    public String getString(String key) {
        return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
    }

    /*
    key 操作
     */
    public Boolean isExistForKey(String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * @Author lilin
     * @Date 2022/9/21 17:35:23
     * @param key 键
     * @param timeout 过期时间，单位是 ms
     * @Return
     * @Description 设置 key 的过期时间
     */
    public void setKey(String key, Long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    public Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    /*
    hash 操作
     */
    public void setHash(String key, String hashKey, String... value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }
    public void setMapHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
    /**
     * @Author lilin
     * @Date 2022/9/21 17:42:37
     * @param key 键
     * @Return
     * @Description 获取指定 key 的所有 hash
     */
    public Map<Object, Object> getAllHashOfKey(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * @Author lilin
     * @Date 2022/9/21 17:45:06
     * @param key 键
     * @param hashKey hash键
     * @Return
     * @Description 删除指定 hash 的 key 的 value
     */
    public void deleteHashForKey(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }
    /**
     * @Author lilin
     * @Date 2022/9/21 17:47:19
     * @param key 键
     * @param hashKey hash键
     * @Return
     * @Description 指定 key 的 hashKey 的值是否存在
     */
    public Boolean isExistForHash(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

}
