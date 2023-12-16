package com.lilin.aspect;

import com.alibaba.fastjson.JSON;
import com.lilin.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiLin on 2022/9/11/15:31:41
 * 切面类
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.lilin.annotation.SystemLog)")
    public void point() {
    }

    @Around("point()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            handlerBefore(joinPoint);
            proceed = joinPoint.proceed();
            handlerAfter(proceed);
        } finally {
            log.info("=========End==========" + System.lineSeparator());
        }
        return proceed;
    }

    private void handlerBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=========Start=========");
        //打印请求 Url
        log.info("URL                : {}", request.getRequestURI());
        //打印描述信息
        log.info("BusinessName       : {}", systemLog.businessName());
        //打印 Http method
        log.info("HTTP method        : {}", request.getMethod());
        //打印调用的 controller 的全路径以及执行方法
        log.info("IP                 : {}-{}", joinPoint.getSignature().getDeclaringTypeName(), (joinPoint.getSignature()).getName());
        //打印请求的 IP
        log.info("IP                 : {}", request.getRemoteHost());
        //打印请求入参
        log.info("Request Args       : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private void handlerAfter(Object proceed) {
        //打印出参
        log.info("Response           : {}", JSON.toJSONString(proceed));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(SystemLog.class);
    }
}