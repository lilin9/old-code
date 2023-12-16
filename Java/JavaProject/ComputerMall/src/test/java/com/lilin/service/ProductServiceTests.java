package com.lilin.service;

import com.lilin.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/15:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    @Test
    public void selectHotList() {
        List<Product> list = productService.selectHotList();
        list.forEach(System.out :: println);
    }

    @Test
    public void selectProductById() {
        System.out.println(productService.selectProductById(10000001));
    }
}
