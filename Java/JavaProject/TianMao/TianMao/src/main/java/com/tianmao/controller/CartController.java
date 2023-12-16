package com.tianmao.controller;

import com.tianmao.service.CartService;
import com.tianmao.utils.JsonResult;
import com.tianmao.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/05/12/17:28
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    CartService cartService;

    @PostMapping({"", "/"})
    public JsonResult<List<CartVo>> getCartVo(HttpSession session) {
        return new JsonResult<>(OK, cartService.selectCartVoByUid(getUidFormSession(session)));
    }

    @GetMapping("updateNum")
    public JsonResult<Long> updateNum(Integer cid, Integer num, HttpSession session) {
        //更新用户数据
        cartService.updateCartNum(getUidFormSession(session), cid, num, getUsernameFromSession(session));

        //查询购物车中的总金额
        List<CartVo> cartVos = cartService.selectCartVoByUid(getUidFormSession(session));
        long allCartPrice = 0;
        for (CartVo item : cartVos) {
            allCartPrice += item.getRealPrice() * item.getNum();
        }
        //将总金额返回给前端
        return new JsonResult<>(OK, allCartPrice);
    }

    @GetMapping("deleteCart/{cid}")
    public JsonResult<Void> deleteCart(@PathVariable Integer cid, HttpSession session) {
        cartService.deleteCartByCid(getUidFormSession(session), cid);
        return new JsonResult<>(OK);
    }

    @PostMapping("add/{pid}")
    public JsonResult<Void> addToCart(@PathVariable Integer pid, Integer num, Long price, HttpSession session) {
        cartService.insert(
                getUidFormSession(session),
                pid,
                getUsernameFromSession(session),
                num,
                price);
        return new JsonResult<>(OK);
    }
}
