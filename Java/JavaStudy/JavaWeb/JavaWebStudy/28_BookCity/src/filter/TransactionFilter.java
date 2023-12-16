package filter;

import utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by MrLi on 2022/01/18/17:45
 *
 * 事务过滤器
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);

            //提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            //回滚事务
            JdbcUtils.rollbackAndClose();

            e.printStackTrace();
            //将异常抛给Tomcat服务器，以展现友好的错误页面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
