import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MrLi on 2021/12/5 14:25
 */
public class HelloServlet2 extends HttpServlet {

    /**
     * @author MrLi
     * @create 2021/12/5 14:27
     * @description 在get请求的时候使用
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("HelloServlet2 的get请求！");
    }

    /**
     * @author MrLi
     * @create 2021/12/5 14:27
     * @description 在post请求的时候使用
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("HelloServlet2 的post请求！");
    }
}
