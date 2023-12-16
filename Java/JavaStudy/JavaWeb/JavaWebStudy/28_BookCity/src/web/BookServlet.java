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
import java.util.List;

/**
 * Created by MrLi on 2022/01/03/18:55
 */
public class BookServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;

        //1、获取请求的参数 == 封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2、调用 BookService.addBook() 保存图书
        bookService.addBook(book);

        /*
        3、跳转到图书列表管理页面（/manager/bookServlet?action=list）
        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        注意：会出现Bug：表单重复提交。
            当用户提交完请求，浏览器会记录下最后一次请求的全部信息，当用户按下功能键F5，会发起浏览器的最后一次请求。
            此时需要使用重定向。
        */
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数 ==> 然后封装称为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2、调用 BookService.updateBook(book) 修改图书
        bookService.updateBook(book);

        //3、重定向回图书列表管理页面（/工程名/manager/BookServlet?action=list）
        resp.sendRedirect(req.getContentType() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2、调用 BookService.queryBookById(id); 得到修改后的Book图书信息
        Book book = bookService.queryBookById(id);

        //3、把图书保存到 Request 域中
        req.setAttribute("book", book);

        //4、请求转发到 /pages/manager/book_edit.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2、调用bookService.deleteBookById(); 删除图书
        bookService.deleteBookById(id);

        //3、重定向回图书列表管理页面（/book/manager/bookServlet?action=list）
        resp.sendRedirect(req.getContentType() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、通过BookServlet查询全部图书信息
        List<Book> books = bookService.queryBooks();

        //2、将全部图书信息保存到Request域中
        req.setAttribute("books", books);

        //3、请求转发到 /pages/manager/book_manager.jsp 页面中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

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

        page.setUrl("manager/bookServlet?action=page");

        //3、保存 page 对象到 request 域中
        req.setAttribute("page", page);

        //4、请求转发到 /page/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/page/manager/book_manager.jsp").forward(req, resp);
    }
}