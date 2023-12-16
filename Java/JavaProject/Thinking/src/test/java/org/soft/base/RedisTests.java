package org.soft.base;

import org.junit.jupiter.api.Test;
import org.soft.base.model.Human;
import org.soft.base.utils.CommonUtils;
import org.soft.base.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by LILIN on 2023/7/31/15:56:53
 */
@SpringBootTest
public class RedisTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CommonUtils commonUtils;

    @Test
    public void redisConnectionTest() {
        stringRedisTemplate.opsForValue().set("k1", "123456");
        String v1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.println("v1 = " + v1);
    }

    @Test
    public void useRedisTest() {
        Double result = redisUtils.incrZSet("k1", "user1", 10.0);
        if (!result.isNaN()) {
            System.out.println("添加 zset 成功");
        } else {
            System.out.println("添加 zset 失败");
        }

        System.out.println("k1 是否存在：" + redisUtils.hasKey("k1"));
        System.out.println("k1 的过期时间是：" + redisUtils.getExpireTime("k1"));

        redisUtils.delete("k1");

        System.out.println("k1 是否存在：" + redisUtils.hasKey("k1"));

    }

    @Test
    public void useRedisForGetTest() {
        String key = "hotArticles";
        String value1 = "article-1";
        String value2 = "article-2";

//        System.out.println("hotArticles::article-1.score = " + redisUtils.score(key, value1));
//        System.out.println("hotArticles::article-2.score = " + redisUtils.score(key, value2));
//
//        System.out.println();
//
//        redisUtils.reverseRange(key, 1, 10).forEach(System.out::println);
//
//        System.out.println();
//
//        redisUtils.reverseRangeWithScores(key, 1, 15).forEach(item -> {
//            System.out.println(item.getValue() + "\t" + item.getScore());
//        });


        Set<Object> sets = redisUtils.reverseRange(key, 1, 10);
        List<Integer> list = commonUtils.removePrefix(sets, "article-");

        sets.forEach(System.out::println);
        System.out.println();
        list.forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void redisExpireTest() {
        Human human = new Human();
        human.setHumanId(1);
        human.setHumanName("tom");
        human.setHumanNiceName("tom");
        human.setHumanPassword("123abc");

        redisUtils.addString("user-" + human.getHumanId(), human.toString());
        boolean result = redisUtils.expireTime("user-1", 10);
        if (result) {
            System.out.println("设置 user-1 过期时间成功");
        } else {
            System.out.println("设置 user-1 过期时间失败");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
            if (redisUtils.hasKey("user-1")) {
                System.out.println("五秒后 user-1 存在");
            } else {
                System.out.println("五秒后 user-1 不存在");
            }

            TimeUnit.SECONDS.sleep(5);
            if (redisUtils.hasKey("user-1")) {
                System.out.println("十秒后 user-1 存在");
            } else {
                System.out.println("十秒后 user-1 不存在");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}











