package org.soft.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by LILIN on 2023/7/31/16:50:08
 * 操作 redis 的工具类
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * @param key  键
     * @param time 过期时间 单位：s
     * @Return false 设置失败；true 设置成功
     * @Description 指定缓存失效时间
     * @Author LILIN
     * @Date 2023/7/31 16:53:18
     */
    public boolean expireTime(String key, long time) {
        if (key.isEmpty() || time <= 0) return false;
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * @param key 键
     * @Return
     * @Description 根据 key 获取过期时间
     * @Author LILIN
     * @Date 2023/7/31 16:59:43
     */
    public long getExpireTime(String key) {
        if (key.isEmpty()) return 0;
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param key 键
     * @Return true 存在；false 不存在
     * @Description 判断 key 是否存在
     * @Author LILIN
     * @Date 2023/7/31 17:01:15
     */
    public boolean hasKey(String key) {
        if (key.isEmpty()) return false;
        return redisTemplate.hasKey(key);
    }

    /**
     * @param keys 键，可以传入多个值
     * @Return
     * @Description 删除 key
     * @Author LILIN
     * @Date 2023/7/31 17:08:45
     */
    public void delete(String... keys) {
        if (keys == null || keys.length == 0) return;

        if (keys.length > 1) {
            redisTemplate.delete(CollectionUtils.arrayToList(keys).toString());
        } else {
            redisTemplate.delete(keys[0]);
        }
    }


    //==========================string=================================

    /**
     * @param key key
     * @param value value
     * @Return
     * @Description 添加一个 string
     * @Author LILIN
     * @Date 2023/8/3 09:11:42
     */
    public void addString(Object key, Object value) {
        if (Objects.isNull(key) && Objects.isNull(value)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }


    //==========================zset=================================

    /**
     * @param key 键
     * @param value 值
     * @param score 分数
     * @Return 返回插入的分数
     * @Description 如果 zset 中的元素存在，就对 score 进行加减；如果元素不存在，则会新插入一个。
     * @Author LILIN
     * @Date 2023/8/1 10:46:44
     */
    public Double incrZSet(String key, Object value, Double score) {
        if (key.isEmpty() || Objects.isNull(value)) return null;
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * @param key 键
     * @param value 值
     * @Return 返回插入的分数
     * @Description 如果 zset 中的元素存在，就对 score 进行加减；如果元素不存在，则会新插入一个。score 默认为 1。
     * @Author LILIN
     * @Date 2023/8/1 10:53:29
     */
    public Double incrZSet(String key, Object value) {
        if (key.isEmpty() || Objects.isNull(value)) return null;
        return redisTemplate.opsForZSet().incrementScore(key, value, 1);
    }

    /**
     * @param key   key
     * @param value value
     * @Return 返回分数，如果没有获取到返回 null
     * @Description 获取 value 对应的分数
     * @Author LILIN
     * @Date 2023/8/1 16:20:28
     */
    public Double score(String key, Object value) {
        if (key.isEmpty() || Objects.isNull(value)) {
            return null;
        }

        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * @param key   key
     * @param start start
     * @param end   end
     * @Return
     * @Description 查询集合中指定顺序的值，0 -1 表示获取全部内容，从大到小排列
     * @Author LILIN
     * @Date 2023/8/1 16:27:35
     */
    public Set<Object> reverseRange(String key, int start, int end) {
        if (key.isEmpty()) {
            return null;
        }
        return redisTemplate.opsForZSet().reverseRange(key, start - 1, end - 1);
    }

    /**
     * @param key   key
     * @param start start
     * @param end   end
     * @Return
     * @Description 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容，从大到小排列
     * @Author LILIN
     * @Date 2023/8/1 16:30:26
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, int start, int end) {
        if (key.isEmpty()) {
            return null;
        }
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start - 1, end - 1);
    }
}










