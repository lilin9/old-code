package com.tianmao.utils;

/**
 * Created by LiLin on 2022/6/2/15:56:21
 *
 * 生成随机数工具类，生成一个四位数的随机数
 */
public class RandomNumber {
    public static String random() {
        return Integer.toString((int) ((Math.random() * 9 + 1) * Math.pow(10, 4 - 1)));
    }
}
