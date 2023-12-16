package com.tianmao.service;

import com.tianmao.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LiLin on 2022/5/29/15:04:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    ProductSerice productSerice;

    @Test
    public void selectAllProduct() {
        List<Product> list = productSerice.selectAllProduct();
        list.forEach(System.out :: println);
    }

    @Test
    public void queryById() {
        System.out.println(productSerice.queryById(10000001));
    }

    @Test
    public void selectProductByPids() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10000011, 10000012));
        productSerice.selectProductByPids(list).forEach(System.out :: println);
    }

    @Test
    public void selectProductByType() {
        productSerice.selectProductByType("记事本").forEach(System.out :: println);
    }
}
