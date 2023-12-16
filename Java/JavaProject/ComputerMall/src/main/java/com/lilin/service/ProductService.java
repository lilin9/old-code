package com.lilin.service;

import com.lilin.pojo.Product;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/15:08
 *
 * 商品数据业务层接口
 */
public interface ProductService {
    /**
     * @Author LiLin
     * @Date 2022/4/14 15:09
     * @return 返回查询到的商品列表
     * @Description 查询销量最多的商品的列表
     */
    List<Product> selectHotList();

    /**
     * @Author LiLin
     * @Date 2022/4/15 9:13
     * @Param id 商品id
     * @return 返回查询到的商品信息
     * @Description 根据id查询商品详细信息
     */
    Product selectProductById(Integer id);
}
