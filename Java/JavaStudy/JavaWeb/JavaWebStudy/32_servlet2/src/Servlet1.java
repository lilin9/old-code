import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrLi on 2021/12/08/9:14
 *
 * 请求的转发
 */
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数（办事的材料） 查看
        String username = req.getParameter("username");
        System.out.println("在Servlet1中查看的参数：" + username);

        //2、为请求的参数盖章，并传送到Servlet2查看
        req.setAttribute("key", "true");

        //3、获取Servlet2的路径
        //请求转发必须以斜杠打头，“/”表示地址为：http://ip:port/工程名/，映射到IDEA代码的web目录
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Servlet2");

        //4、传送数据到Servlet2
        requestDispatcher.forward(req, resp);
    }
}
