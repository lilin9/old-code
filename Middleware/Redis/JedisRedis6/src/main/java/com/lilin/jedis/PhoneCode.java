package com.lilin.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * Created by LiLin on 2022/7/28/15:41:54
 *
 * 手机验证码测试
 */
public class PhoneCode {
    //创建 jedis 对象
    private final static Jedis jedis = new Jedis("192.168.200.129", 6379);

    public static void main(String[] args) {
        //获取验证码
        String code = getCode();
        //设置手机号
        String phone = "18276360056";
        //保存验证码
        saveCode("18276360056", code);
        //校验验证码
//        checkCode(phone, code);
        checkCode(phone, "123456");
    }

    //用来生成 6 位的数字码
    public static String getCode() {
        Random random = new Random();

        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomCode.append(random.nextInt(10));
        }
        return randomCode.toString();
    }

    //保存验证码到 redis 中
    //每部手机每天只能发送三次
    //设置过期时间
    public static void saveCode(String phoneNumber, String code) {
        //拼接key
        //手机发送次数 key
        String countKey = "VerifyCode" + phoneNumber + ":count";
        //验证码key
        String codeKey = "VerifyCode" + phoneNumber + ":code";

        //每部手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24*3600, "1");
        } else if (Integer.parseInt(count) <= 2) {
            //发送次数加1
            jedis.incr(countKey);
        } else {
            System.out.println("您今天的请求次数已经超出限制");
            jedis.close();
            return;
        }

        //将验证码存入 redis
        jedis.setex(codeKey, 120, code);
        jedis.close();
    }

    //校验验证码
    public static void checkCode(String phoneNumber, String code) {
        //从redis中获取验证码
        //验证码key
        String codeKey = "VerifyCode" + phoneNumber + ":code";
        String redisCode = jedis.get(codeKey);

        //判断
        String result = redisCode.equals(code) ? "验证成功" : "验证失败";
        System.out.println(result);
        jedis.close();
    }
}
