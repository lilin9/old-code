package com.lilin.service.impl;

import com.lilin.mapper.ProductMapper;
import com.lilin.pojo.Product;
import com.lilin.service.ProductService;
import com.lilin.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/04/14/15:10
 *
 * 商品数据业务层接口的实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * @Author LiLin
     * @Date 2022/4/14 15:09
     * @return 返回查询到的商品列表
     * @Description 查询销量最多的商品的列表
     */
    @Override
    public List<Product> selectHotList() {
        List<Product> productList = productMapper.selectHotList();

        for (Product product : productList) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }

        return productList;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/15 9:13
     * @Param id 商品id
     * @return 返回查询到的商品信息
     * @Description 根据id查询商品详细信息
     */
    @Override
    public Product selectProductById(Integer id) {
        Product product = productMapper.selectProductById(id);

        if (product == null)
            throw new ProductNotFoundException("尝试访问的商品信息不存在");

        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
}
