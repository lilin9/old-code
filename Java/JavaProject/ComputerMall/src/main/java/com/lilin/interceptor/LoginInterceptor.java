package com.lilin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiLin on 2022/04/06/15:31
 *
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @author LiLin
     * @create 2022/4/6 15:36
     * @description 在调用所有请求处理的方法之前被自动调用执行的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //查询session域中的uid
        Object uid = request.getSession().getAttribute("uid");
        //判断uid是否为空
        if (uid != null) {
            //如果不为空就放行
            return true;
        }
        //如果为空就重定向到登录页面
        response.sendRedirect("/web/login.html");
        //打印提示信息
        //response.getWriter().write("请先登录");
        //结束后续操作，不放行
        return false;
    }

    //在ModelAndView对象返回之后被调用的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //在整个请求所有关联的资源被执行完毕最后所执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
