package com.lilin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by LiLin on 2022/04/19/22:15
 *
 * 统计时间耗时的切面类
 */

@Component  //将当前类的对象创建使用与维护交由Spring容器管理
@Aspect     //将当前类标记为切面类
public class TimerAspect {

    @Around("execution(* com.lilin.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //记录当前时间
        long start = System.currentTimeMillis();

        Object result = pjp.proceed(); //调用目标方法，例如：login

        //记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("耗时 --> " + (end-start) + "ms");
        return result;
    }
}
