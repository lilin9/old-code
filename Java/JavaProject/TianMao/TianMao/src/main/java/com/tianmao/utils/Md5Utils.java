package com.tianmao.utils;

import org.springframework.util.DigestUtils;

/**
 * Created by MrLi on 2022/03/09/15:38
 */
public class Md5Utils {
    /**
     * 1、MD(message-digest algorithm 5) 消息摘要算法
     *  它的长度一般是32位的16进制字符串
     * 2、由于系统密码明文储存容易被黑客盗取
     * 3、应用：注册时，将密码进行md5加密，储存到数据库，可以防止看到数据库数据的人去恶意篡改；
     *         登录时，将密码进行md5加密，与储存在数据库中加密过的密码进行对比
     * 4、md5不可逆，即没有对应的算法，从产生的md5值逆向得到原始数据。
     *    但是可以暴力破解，这里的暴力破解并非把摘要还原成原始数据，如暴力枚举法
     */
    public static String getMd5Password(String password, String salt) {
        //进行三次md5算法加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
