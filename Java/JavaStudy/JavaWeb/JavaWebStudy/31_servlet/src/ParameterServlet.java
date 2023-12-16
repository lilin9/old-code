import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrLi on 2021/12/07/8:53
 */
public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hobby = req.getParameter("hobby");

        System.out.println("name: " + username + "\n" + "password: " + password + "\n" + "hobby: " + hobby);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置请求体的字符集为utf-8
        //需要在获取请求参数之前调用才有效
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hobby = req.getParameter("hobby");

        System.out.println("name: " + username + "\n" + "password: " + password + "\n" + "hobby: " + hobby);
    }
}
