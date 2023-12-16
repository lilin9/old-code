package org.soft.base.interceptor;

import org.soft.base.model.Human;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by LILIN on 2023/8/1/21:08:03
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //取得session
        HttpSession session = request.getSession();
        //获取 human 对象
        Human human = (Human) session.getAttribute("human");
        //判断用户有没有登录
        if (!Objects.isNull(human)) {
            response.sendRedirect("/index.html");
            return true;
        }
        //重定向登录页面
        response.sendRedirect("/view/login.html");
        return false;
    }
}
