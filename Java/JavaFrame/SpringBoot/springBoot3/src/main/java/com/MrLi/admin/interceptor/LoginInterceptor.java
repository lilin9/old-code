package com.MrLi.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by MrLi on 2022/03/24/9:03
 */

//登录检查拦截器
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @author MrLi
     * @create 2022/3/24 9:05
     * @description 目标方法执行之前
     *
     * 登录检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        //如果用户有登录，返回true，放行
        if (loginUser != null) return true;
        //如果用户没有登录，返回false，不放行
        //并且跳转到登录页
//        session.setAttribute("msg", "用户未登录");
//        response.sendRedirect("/");
        request.setAttribute("msg", "用户未登录");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

    /**
     * @author MrLi
     * @create 2022/3/24 9:05
     * @description 目标方法执行之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * @author MrLi
     * @create 2022/3/24 9:06
     * @description 页面渲染完成以后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
