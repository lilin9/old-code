package com.lilin.controller;

import com.lilin.pojo.Product;
import com.lilin.service.ProductService;
import com.lilin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/15:28
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    ProductService productService;

    @GetMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        return new JsonResult<>(OK, productService.selectHotList());
    }

    @GetMapping("get_product/{id}")
    public JsonResult<Product> getProduct(@PathVariable("id") Integer id) {
        return new JsonResult<>(OK, productService.selectProductById(id));
    }
}
