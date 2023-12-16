package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrLi on 2022/01/14/16:35
 */
public class CartServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();

    /**
     * @author MrLi
     * @create 2022/1/14 16:38
     * @description 加入购物车
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数：
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用 bookService.queryBookById(id) 得到图书的信息
        Book book = bookService.queryBookById(id);

        //将图书信息转换成为 CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getBookName(), 1, new BigDecimal(book.getPrice()), new BigDecimal(book.getPrice()));

        //调用 Cart.addItem(CartItem) 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());

        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @author MrLi
     * @create 2022/1/14 16:38
     * @description 删除购物车
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            //删除商品项
            cart.deleteItem(id);
            //重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @author MrLi
     * @create 2022/1/14 16:38
     * @description 清空购物车
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            //清空购物车
            cart.clear();
            //重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @author MrLi
     * @create 2022/1/14 16:38
     * @description 修改商品数量
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数：商品编号、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        //获取购物车Cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id, count);
            //重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @author MrLi
     * @create 2022/1/14 16:38
     * @description 修改添加商品到购物车
     */
    protected void ajaAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数：
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用 bookService.queryBookById(id) 得到图书的信息
        Book book = bookService.queryBookById(id);

        //将图书信息转换成为 CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getBookName(), 1, new BigDecimal(book.getPrice()), new BigDecimal(book.getPrice()));

        //调用 Cart.addItem(CartItem) 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());

        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String toJson = gson.toJson(resultMap);
        resp.getWriter().write(toJson);
    }
}
















