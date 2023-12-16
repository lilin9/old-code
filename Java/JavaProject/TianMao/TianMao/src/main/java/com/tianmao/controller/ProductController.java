package com.tianmao.controller;

import com.tianmao.pojo.Product;
import com.tianmao.service.ProductSerice;
import com.tianmao.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/5/29/11:22:00
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {
    @Autowired
    ProductSerice productSerice;

    //获取所有商品数据
    @PostMapping({"", "/"})
    public JsonResult<List<Product>> getAllProduct() {
        List<Product> list = productSerice.selectAllProduct();
        return new JsonResult<>(OK, list);
    }

    //根据商品id获取商品数据
    @PostMapping("getProduct/{id}")
    public JsonResult<Product> getProduct(@PathVariable Integer id) {
        return new JsonResult<>(OK, productSerice.queryById(id));
    }

    //修改商品数量
    @GetMapping("updateNum/{id}")
    public JsonResult<Void> updateNum(@PathVariable Integer id, Integer num, HttpSession session) {
        productSerice.updateNum(id, num, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    //根据商品类别获得商品数据
    @PostMapping("getProductByType")
    public JsonResult<List<Product>> getProductByType(String type) {
        return new JsonResult<>(
                OK,
                productSerice.selectProductByType(type));
    }
}
