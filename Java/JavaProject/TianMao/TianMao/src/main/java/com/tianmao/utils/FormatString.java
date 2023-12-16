package com.tianmao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiLin on 2022/5/30/23:02:14
 *
 * 格式化字符串，将字符串转换为Integer类型的list数组
 *
 * pid=10000019&pid=10000013
 */
public class FormatString {
    public List<Integer> format(String str) {
        String[] strs = str.split("&");
        ArrayList<Integer> result = new ArrayList<>();
        for (String item : strs) {
            int value = Integer.parseInt(item.split("=")[1]);
            result.add(value);
        }
        return result;
    }
}
