package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Created by MrLi on 2022/01/16/17:18
 */
public class AdminFilter implements Filter {
    public AdminFilter() {
        System.out.println("（1）构造器方法");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("（2）init初始化方法");

        //（1）获取 filter-name 的内容
        System.out.println("获取 filter-name 的内容：" + filterConfig.getFilterName());

		//（2）获取 web.xml 中 init-param 初始化参数
        System.out.println("获取 init-param 初始化参数：" + filterConfig.getInitParameter("username"));

		//（3）获取 ServletContext 对象
        System.out.println("获取 ServletContext 对象：" + filterConfig.getServletContext());
    }

    /**
     * @author MrLi
     * @create 2022/1/16 17:19
     * @description 用于拦截请求，以及权限检查
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("（3）doFilter过滤方法");

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            return;
        } else {
            //让程序继续往下访问用户的目标资源
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("（4）destroy销毁方法");
    }
}
