import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MrLi on 2021/12/08/9:16
 */
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        //1、获取请求的参数（办事的材料） 查看
        String username = req.getParameter("username");
        System.out.println("在Servlet2中查看的参数：" + username);

        //2、查看Servlet1的数据是否盖章
        Object key = req.getAttribute("key");
        System.out.println("数据是否被Servlet1标记：" + key);

        //3、处理自己的业务
        System.out.println("处理自己的业务");
    }
}
