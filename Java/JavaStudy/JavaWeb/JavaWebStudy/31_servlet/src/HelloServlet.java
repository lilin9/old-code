import javax.servlet.*;

public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) {
        //1、可以获取Servlet程序的别名 servlet-name 的值
        System.out.println("Servlet程序的别名是：" + servletConfig.getServletName());

        //2、获取初始化参数 init-param
        System.out.println("初始化参数 username 的值是：" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数 url 的值是：" + servletConfig.getInitParameter("url"));

        //3、获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service() 方法专门用来处理请求和响应
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("Hello Servlet 被访问了！！");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
