package com.lilin.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/9/5/11:33:07
 * 拷贝 vo 类的工具类
 */
public class BeanCopyUtils {
    public BeanCopyUtils() {
    }

    /**
     * @Author lilin
     * @Date 2022/9/5 13:26:00
     * @param source 需要拷贝的对象
     * @param clazz 要拷贝成的类型
     * @Return
     * @Description 拷贝单个对象
     */
    public static <T>T copyBean(Object source, Class<T> clazz) {
        T object_ = null;
        try {
            //创建实例
            object_ = clazz.getDeclaredConstructor().newInstance();
            //实现属性 copy
            BeanUtils.copyProperties(source, object_);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return object_;
    }

    public static <T, O> List<T> copyBeanList(List<O> sourceList, Class<T> clazz) {
        return sourceList.stream().map(source -> copyBean(source, clazz)).collect(Collectors.toList());
    }
}
