package com.lilin.controller;

import com.lilin.controller.exception.*;
import com.lilin.controller.exception.father.FileUploadException;
import com.lilin.service.exception.*;
import com.lilin.service.exception.father.ServiceException;
import com.lilin.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * Created by LiLin on 2022/04/05/16:18
 * <p>
 * 控制层类的基类
 */
public class BaseController {
    public static final Integer OK = 200;

    /**
     * @return 返回值是需要传递给前端的数据，即JSON数据
     * @author LiLin
     * @create 2022/4/5 16:22
     * @description 用于请求中管理异常的统一方法
     * <p>
     * 自动将异常对象传递给此方法的参数列表上
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //指统一处理请求过程中抛出的异常
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);

        if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册用户信息时产生未知错误");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新用户信息时产生未知错误");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMessage("删除收货地址信息时产生未知错误");
        } else if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名已被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户信息不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户密码错误");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户收货地址超出上限");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4004);
            result.setMessage("用户数据被非法访问");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4005);
            result.setMessage("收货地址不存在");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("尝试访问的商品信息不存在");
        } else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("尝试访问的购物车信息不存在");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小超出限制");
        } else if (e instanceof FileStatusException) {
            result.setState(6002);
            result.setMessage("文件状态异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage("文件类型错误");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("读取文件失败");
        }
        return result;
    }

    /**
     * @return 返回uid
     * @author LiLin
     * @create 2022/4/6 14:48
     * @description 从session域中获取用户的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * @return 返回用户名
     * @author LiLin
     * @create 2022/4/6 14:53
     * @description 从session中获取用户的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
