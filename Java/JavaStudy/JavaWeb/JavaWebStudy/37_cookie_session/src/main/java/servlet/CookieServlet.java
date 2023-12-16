package servlet;

import utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/logout")

/**
 * Created by MrLi on 2022/01/11/16:36
 */
public class CookieServlet extends BaseServlet {
    public void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、创建Cookie对象
        Cookie cookie = new Cookie("key1", "value1");

        //2、通知客户端保存Cookie
        resp.addCookie(cookie);

        //1、创建Cookie对象
        Cookie cookie1 = new Cookie("key2", "value2");

        //2、通知客户端保存Cookie
        resp.addCookie(cookie1);

        resp.getWriter().write("Cookie创建成功！");
    }

    public void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie :
                cookies) {
            System.out.println("Cookie["+ cookie.getName() + "=" + cookie.getValue() +"]");
        }

        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);
        if (iWantCookie != null) {
            resp.getWriter().write("找到了想要的Cookie，" + iWantCookie.getName() + "的值是：" + iWantCookie.getValue());
        }
    }

    public void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //方案一：
        //1、先创建一个要修改的同名的Cookie对象
        //2、在构造器中同时赋予新的Cookie值
        //3、调用response.addCookie(cookie);保存到客户端
        /*Cookie cookie = new Cookie("key1", "newValue1");
        resp.addCookie(cookie);*/

        //方案二：
        //1、先查找到需要修改的Cookie对象
        //2、调用setValue()方法赋予新的Cookie值
        //3、调用response.addCookie()通知客户端保存修改
        Cookie cookie = CookieUtils.findCookie("key2", req.getCookies());
        if (cookie != null) cookie.setValue("newValue2");
        resp.addCookie(cookie);


        resp.getWriter().write("Cookie已经修改成功！");
    }

    public void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        cookie.setMaxAge(-3);
        resp.addCookie(cookie);
    }

    public void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、找到要删除的Cookie对象
        //2、调用setMaxAge(0)
        //3、调用response.addCookie()通知客户端保存修改
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    public void setPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("pathKey1", "pathValue1");

        //req.getContextPath()：获取工程路径
        // /工程路径/abc
        cookie.setPath(req.getContextPath() + "/abc");
        resp.addCookie(cookie);

        resp.getWriter().write("创建了一个带有Path路径的Cookie！");
    }
}
