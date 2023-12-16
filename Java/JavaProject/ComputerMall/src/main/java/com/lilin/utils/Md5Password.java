package com.lilin.utils;

import org.springframework.util.DigestUtils;

/**
 * Created by LiLin on 2022/04/05/15:16
 *
 * 通过Md5将密码进行加密操作
 */
public class Md5Password {

    /**
     * @author LiLin
     * @create 2022/4/5 15:17
     * @return 返回加密后的密码字符串
     * @description 通过Md5加密算法对密码进行加密操作
     */
    public static String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //进行三次加密
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
