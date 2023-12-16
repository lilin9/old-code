package com.tianmao.controller.strategy;

import java.util.HashMap;

/**
 * Created by LiLin on 2022/05/20/13:02
 *
 * 工厂模式：通过 ExceptionFactory 来获取各个异常策略
 */
public class ExceptionFactory {
    private static final HashMap<String, ExceptionHandler> map = new HashMap<>();

    //定义一个获取方法
    public static ExceptionHandler getExceptionHandler(String name) {
        return map.get(name);
    }

    //定义一个注册方法
    public static void registerException(String name, ExceptionHandler handler) {
        if (name.isEmpty() || handler == null) return;
        map.put(name, handler);
    }
}
