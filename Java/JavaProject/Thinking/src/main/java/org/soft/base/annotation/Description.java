package org.soft.base.annotation;

import lombok.Data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LILIN on 2023/8/8/14:09:03
 * 自定义注解：收集每个方法的注释信息
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    String message() default "";
}
