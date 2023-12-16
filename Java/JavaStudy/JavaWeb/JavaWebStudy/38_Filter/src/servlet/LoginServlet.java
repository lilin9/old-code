package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by MrLi on 2022/01/18/13:32
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("abc".equalsIgnoreCase(username) && "123".equals(password)) {
            req.getSession().setAttribute("username", username);
            resp.getWriter().write("登陆成功！");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
