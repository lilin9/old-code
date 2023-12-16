package com.tianmao.mapper;

import com.tianmao.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiLin on 2022/5/30/19:35:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    ProductMapper productMapper;
    @Test
    public void selectProductByPid() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10000001);
        list.add(10000002);
        list.add(10000003);
        List<Product> resultList = productMapper.selectProductByPids(list);
        resultList.forEach(System.out :: println);
    }

    @Test
    public void selectProductsByType() {
        productMapper.selectProductsByType("记事本").forEach(System.out :: println);
    }
}
