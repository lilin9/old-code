package com.tianmao.controller;

import com.tianmao.pojo.Order;
import com.tianmao.service.OrderService;
import com.tianmao.utils.FormatString;
import com.tianmao.utils.JsonResult;
import com.tianmao.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/05/16/12:01
 */
@RestController
@RequestMapping("order")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;

    @PostMapping({"", "/"})
    public JsonResult<List<CartVo>> getCartVo(@RequestParam("pids") String pids, HttpSession session) {
        //对字符串类型的pids进行处理，转换成Integer类型的list集合
        FormatString formatString = new FormatString();
        List<Integer> pidsList = formatString.format(pids);
        //根据商品id集合查询商品数据
        return new JsonResult<>(OK,
                orderService.selectCartVoByCids(getUidFormSession(session), pidsList));
    }

    @PostMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, orderService.createOrder(
                aid,
                getUidFormSession(session),
                cids,
                getUsernameFromSession(session)));
    }
}
