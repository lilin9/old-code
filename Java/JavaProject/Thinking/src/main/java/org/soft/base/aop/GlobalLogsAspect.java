package org.soft.base.aop;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.soft.base.annotation.Description;
import org.soft.base.enums.LogTypeEnum;
import org.soft.base.model.Human;
import org.soft.base.model.ResponseResult;
import org.soft.base.utils.CommonUtils;
import org.soft.base.vo.LogsVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by LILIN on 2023/8/8/11:19:59
 * 全局日志切面类
 */
@Aspect
@Component
@Slf4j
public class GlobalLogsAspect {
    @Value("${my.requestUrl}")
    private String requestUrl;

    //方法执行开始时间
    private String startTime;
    //方法执行时间
    private String spendTime;

    //公共工具类
    private final CommonUtils commonUtils;

    public GlobalLogsAspect(CommonUtils commonUtils) {
        this.commonUtils = commonUtils;
    }

    /**
     * @Return
     * @Description 定义切入点，拦截 server 包下的所有方法
     * @Author LILIN
     * @Date 2023/8/8 11:28:22
     */
    @Pointcut("execution(* org.soft.base.server..*.*(..))")
    public void logPointCut() {
    }

    /**
     * @param joinPoint joinPoint
     * @param returns   returns
     * @Return
     * @Description 后置通知，记录方法执行情况相关的日志信息
     * @Author LILIN
     * @Date 2023/8/8 14:51:01
     */
    @AfterReturning(value = "logPointCut()", returning = "returns")
    public void interceptOperateLogs(JoinPoint joinPoint, Object returns) {
        sendMessage(joinPoint, returns, null);
    }

    /**
     * @param joinPoint joinPoint
     * @param exception exception
     * @Return
     * @Description 后置通知，记录和异常有关的日志信息
     * @Author LILIN
     * @Date 2023/8/8 16:16:31
     */
    @AfterThrowing(value = "logPointCut()", throwing = "exception")
    public void interceptExceptionLogs(JoinPoint joinPoint, Throwable exception) {
        sendMessage(joinPoint, null, exception);
    }

    /**
     * @param joinPoint joinPoint
     * @param returns   returns
     * @param exception exception
     * @Return
     * @Description 向指定路径发送 http 请求
     * @Author LILIN
     * @Date 2023/8/9 09:39:59
     */
    private void sendMessage(JoinPoint joinPoint, Object returns, Throwable exception) {
        //得到日志实体类
        LogsVo logsVo = getLogsVo(joinPoint, returns, exception);
        //如果为空，不执行后面的代码
        if (Objects.isNull(logsVo)) {
            return;
        }

        //发送请求
        ResponseResult<Object> responseResult = commonUtils.httpRequest(requestUrl, HttpMethod.POST, logsVo);
        //处理响应体
        if (responseResult != null && responseResult.getCode() == 200) {
            log.info("{} 日志发送成功 {}", commonUtils.getNowDate(), logsVo);
        } else {
            log.info("{} 日志发送失败 {}", commonUtils.getNowDate(), responseResult);
        }
    }

    /**
     * @param joinPoint joinPoint
     * @param returns   returns
     * @param exception exception
     * @Return
     * @Description 根据传入的参数生成一个 LogsVo 类
     * @Author LILIN
     * @Date 2023/8/8 16:02:48
     */
    private LogsVo getLogsVo(JoinPoint joinPoint, Object returns, Throwable exception) {
        //获取 RequestAttribute
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        //从 RequestAttribute 中获取 HttpServletRequest 的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        //声明一个日志 vo 类
        LogsVo logsVo = new LogsVo();
        //从切入点获取切入点处的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Method method = methodSignature.getMethod();

        //从自定义注解获取接口描述
        if (method.isAnnotationPresent(Description.class)) {
            Description description = method.getAnnotation(Description.class);
            logsVo.setDescription(description.message());
        }

        //设置当前登录用户名
        Human human = (Human) request.getSession().getAttribute("human");
        logsVo.setUsername(human.getHumanName());

        //设置当前方法操作时间
        logsVo.setStartTime(startTime);
        //设置消耗时间
        logsVo.setSpendTime(spendTime);
        //设置请求链接
        logsVo.setUrl(request.getRequestURI());
        //设置请求方法(get、post...)
        logsVo.setMethod(request.getMethod().toLowerCase());
        //设置请求 IP 地址
        logsVo.setIp(commonUtils.getIpAddress(request));
        //设置请求参数
        logsVo.setParameter(getParameters(method, joinPoint.getArgs()));
        //设置请求设备信息
        logsVo.setDevice(request.getHeader("User-Agent"));

        //设置请求返回结果
        if (!Objects.isNull(returns)) {
            logsVo.setResult(returns);
        }

        //如果传入了异常，执行以下操作
        if (!Objects.isNull(exception)) {
            //设置日志类型
            logsVo.setType(LogTypeEnum.ERROR.toString().toLowerCase());

            //设置异常名
            logsVo.setExceptionMsg(exception.getClass().getName());
            //设置异常信息
            logsVo.setExceptionMsg(stackTraceToString(exception.getClass().getName(), exception.getMessage(), exception.getStackTrace()));
        } else {
            //设置日志类型
            logsVo.setType(LogTypeEnum.INFO.toString());
        }

        return logsVo;
    }

    /**
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     * @Return
     * @Description TODO
     * @Author LILIN
     * @Date 2023/8/8 16:10:21
     */
    public String stackTraceToString(String exceptionName,
                                     String exceptionMessage,
                                     StackTraceElement[] elements) {
        StringBuilder stringBuffer = new StringBuilder();
        for (StackTraceElement stet : elements) {
            stringBuffer.append(stet).append("\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + stringBuffer;
    }

    /**
     * @param method method
     * @param args   args
     * @Return
     * @Description 根据方法和传入的参数，获取请求参数
     * @Author LILIN
     * @Date 2023/8/8 15:29:02
     */
    public Object getParameters(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            //过滤掉参数中可能包含的 ServletRequest、ServletResponse等内容
            if (args[i] instanceof ServletRequest ||
                args[i] instanceof ServletResponse ||
                args[i] instanceof MultipartFile) {
                continue;
            }

            //将 @RequestBody 注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            //将 @RequestParam 注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);

            //获取参数名
            String key = parameters[i].getName();
            if (!Objects.isNull(requestBody)) {
                argList.add(args[i]);
            } else if (!Objects.isNull(requestParam)) {
                map.put(key, args[i]);
            } else {
                map.put(key, args[i]);
            }
        }

        if (map.size() > 0) {
            argList.add(map);
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

    /**
     * @param joinPoint joinPoint
     * @Return
     * @Description 环绕通知，获取一个方法的开始时间和执行时间
     * @Author LILIN
     * @Date 2023/8/8 14:54:31
     */
    @Around(value = "logPointCut()")
    public Object getProcessTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前时间与当前格式化时间
        long start = System.currentTimeMillis();
        startTime = commonUtils.getNowDate();

        //执行当前方法
        Object proceed = joinPoint.proceed();

        //获取方法执行后的时间
        long end = System.currentTimeMillis();
        spendTime = (end - start) + " ms";

        return proceed;
    }
}
