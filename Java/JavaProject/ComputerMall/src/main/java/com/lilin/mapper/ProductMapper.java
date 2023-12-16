package com.lilin.mapper;

import com.lilin.pojo.Product;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/14:44
 *
 * 热销商品 mapper
 */
public interface ProductMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/14 14:45
     * @return 返回查询到的商品列表
     * @Description 查询热销商品
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
