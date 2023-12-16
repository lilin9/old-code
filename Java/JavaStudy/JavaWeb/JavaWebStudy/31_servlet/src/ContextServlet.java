import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MrLi on 2021/12/5 18:01
 */
public class ContextServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = getServletConfig().getServletContext();

        //1、获取web.xml中配置的上下文参数 context-param
        String username = context.getInitParameter("username");
        System.out.println(username);

        /*2、获取当前的工程路径，格式： /工程路径
        System.out.println(context.getContextPath());

        context.getContextPath()报错的原因：
            因为servlet-api是servlet 3.0 版本之前的地址，javax.servlet-api 是servlet 3.0 版本之后的地址。
            所以servlet-api资源中的HttpServletRequest对象中没有getServletContext()方法
            需要将servlet-api依赖换成javax-servlet-api
        */

        //3、获取工程部署后在服务器硬盘上的绝对路径
        String realPath = context.getRealPath("/");
        System.out.println(realPath);
    }
}
