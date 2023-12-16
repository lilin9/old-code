package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by MrLi on 2022/01/13/14:13
 */
public class SessionServlet extends BaseServlet {
    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建和获取一个session会话对象
        HttpSession session = req.getSession();

        String id = session.getId();

        if (session.isNew()) {
            resp.getWriter().write("刚刚创建了一个session会话对象，它的ID是：" + id);
        } else {
            resp.getWriter().write("刚刚获取了一个session会话对象，它的ID是：" + id);
        }
    }

    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key1", "session1");
        resp.getWriter().write("设置了一个session的值！");
    }

    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object key1 = req.getSession().getAttribute("key1");
        resp.getWriter().write("获取了一个session的值为" + key1);
    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int time = req.getSession().getMaxInactiveInterval();
        resp.getWriter().write("session的默认超时时长是：" + time + "s");
    }

    protected void threeSecondLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取session对象
        HttpSession session = req.getSession();

        //2、设置session对象超时时长为3秒
        session.setMaxInactiveInterval(3);

        resp.getWriter().write("设置了session的默认超时时长为3s！");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.getWriter().write("当前session会话对象立马失效超时！");
    }
}
