package com.lilin.mapper;

import com.lilin.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/14:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    ProductMapper productMapper;

    @Test
    public void selectHotList() {
        List<Product> list = productMapper.selectHotList();

        list.forEach(System.out :: println);
    }

    @Test
    public void selectProductById() {
        System.out.println(productMapper.selectProductById(10000001));
    }
}
