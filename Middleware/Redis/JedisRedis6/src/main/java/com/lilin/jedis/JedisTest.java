package com.lilin.jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by LiLin on 2022/7/28/14:54:06
 */
public class JedisTest {
    public static void main(String[] args) {
        //创建 jedis 对象
        Jedis jedis = new Jedis("192.168.200.129", 6379);
        //测试连接
        System.out.println("连接成功 --> " + jedis.ping());
        //关闭 jedis
        jedis.close();
    }
}
