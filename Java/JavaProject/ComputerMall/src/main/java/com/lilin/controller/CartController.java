package com.lilin.controller;

import com.lilin.service.CartService;
import com.lilin.utils.JsonResult;
import com.lilin.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/04/15/15:45
 *
 * 购物车控制层的控制类
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    CartService cartService;

    @PostMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.insert(getUidFromSession(session), pid, amount, getUsernameFromSession(session));
        return new JsonResult<>(OK);

    }

    @GetMapping({"", "/"})
    public JsonResult<List<CartVo>> getCartVo(HttpSession session) {
        return new JsonResult<>(OK, cartService.selectVOByUid(getUidFromSession(session)));
    }

    @PostMapping("add_num/{cid}")
    public JsonResult<Integer> addCartNum(@PathVariable("cid") Integer cid, HttpSession session) {
        return new JsonResult<>(OK, cartService.addCartNum(cid, getUidFromSession(session), getUsernameFromSession(session)));
    }

    @PostMapping("list")
    public JsonResult<List<CartVo>> getCartVoList(Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, cartService.selectVOByCidList(getUidFromSession(session), cids));
    }
}
