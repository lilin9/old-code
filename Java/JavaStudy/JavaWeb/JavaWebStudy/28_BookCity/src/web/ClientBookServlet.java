package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrLi on 2022/01/11/13:09
 */
public class ClientBookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();
    /**
     * @author MrLi
     * @create 2022/1/9 15:50
     * @description 处理分页功能
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2、调用 BookService.page(pageNo, pageSize)，返回一个 page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        //3、保存 page 对象到 request 域中
        req.setAttribute("page", page);

        //4、请求转发到 /page/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/page/client/index.jsp").forward(req, resp);
    }

    /**
     * @author MrLi
     * @create 2022/1/11 14:14
     * @description 处理价格分页功能
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数：pageNo、pageSize、min、max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2、调用bookService.pageByPrice(pageNo, pageSize, min, max)：page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");

        //如果有最小价格的参数，追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        //如果有最大价格的参数，追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(stringBuilder.toString());

        //3、保存分页对象到Request域中
        req.setAttribute("page", page);

        //4、请求转发到 /pages/client/index.jsp 页面
        req.getRequestDispatcher("/page/client/index.jsp").forward(req, resp);
    }
}
