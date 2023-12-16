package com.tianmao.controller;

import com.tianmao.controller.exception.fileException.father.FileUploadException;
import com.tianmao.controller.strategy.ExceptionFactory;
import com.tianmao.service.exception.father.ServiceException;
import com.tianmao.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * Created by LiLin on 2022/04/10/16:27
 *
 * 控制层基类（后期需对大量的 if/else 进行优化）
 */
public class BaseController {
    //设置正常响应码
    public static final Integer OK = 200;

    /**
     * @Author LiLin
     * @Date 2022/4/10 16:34
     * @Param e 异常类型
     * @return 返回值是需要传递给前端的数据，即JSON数据
     * @Description 用于请求中管理异常的统一方法，自动将异常对象传递给此方法的参数列表上
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //指统一处理请求过程中抛出的异常
    public JsonResult<Void> handlerException(Throwable e) {
        //获取异常类名
        String name = e.getClass().getName();
        //先获取当前异常的策略实现类（即具体是哪个异常）
        //再执行 run() 返回 JsonResult
        return ExceptionFactory.getExceptionHandler(name).run(e);
    }

    /**
     * @Author LiLin
     * @Date 2022/4/29 11:03
     * @Param session session对象
     * @return 返回获取到的用户id
     * @Description 通过session获取用户id
     *
     * protected：只能在 BaseController.java类或者其继承类中使用
     */
    protected static Integer getUidFormSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * @Author LiLin
     * @Date 2022/4/29 11:03
     * @Param session session对象
     * @return 返回获取到的用户名
     * @Description 通过session获取用户名
     *
     * protected：只能在 BaseController.java类或者其继承类中使用
     */
    protected static String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
