package com.pcjp.crm.workbench.web.interceptor;

import com.pcjp.crm.commons.contants.Contans;

import com.pcjp.crm.workbench.domain.Admin;
import com.pcjp.crm.workbench.domain.TUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/8 9:48
 * @Version 1.0
 **/
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    //在目标资源之前拦截
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //登录验证
        //如果用户没有登录成功，则跳转到登录页面
        HttpSession session=httpServletRequest.getSession();
        Admin admin=(Admin) session.getAttribute(Contans.SESSION_USER);
        if(admin==null){
            System.out.println("===================="+httpServletRequest.getContextPath()+"====================");
            httpServletResponse.sendRedirect("/");//重定向时，url必须加项目的名称
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
