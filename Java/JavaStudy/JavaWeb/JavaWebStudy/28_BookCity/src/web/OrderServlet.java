package web;

import pojo.Cart;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrLi on 2022/01/16/15:28
 */
public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 生产订单
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取 cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //获取 userId
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();

        //调用 orderService.createOrder(cart, userId)
        String orderId = orderService.createOrder(cart, userId);

        //保存到 Request 域中
        //req.setAttribute("orderId", orderId);
        req.getSession().setAttribute("orderId", orderId);

        //请求转发到 /pages/cart/checkout.jsp 中
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 查看所有订单
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 订单状态：发货/未发
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 查看订单详情
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 查看我的订单
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * @author MrLi
     * @create 2022/1/16 15:31
     * @description 签收订单/确认收货
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
