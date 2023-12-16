package com.lilin.controller;

import com.lilin.pojo.Order;
import com.lilin.service.OrderService;
import com.lilin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by LiLin on 2022/04/19/19:21
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, orderService.createOrder(aid, getUidFromSession(session), cids, getUsernameFromSession(session)));
    }
}
