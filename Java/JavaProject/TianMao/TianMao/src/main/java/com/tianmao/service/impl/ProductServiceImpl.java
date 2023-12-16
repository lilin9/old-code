package com.tianmao.service.impl;

import com.tianmao.mapper.ProductMapper;
import com.tianmao.pojo.Product;
import com.tianmao.service.ProductSerice;
import com.tianmao.service.exception.ProductNotFoundException;
import com.tianmao.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/17 21:08
 **/
@Service
public class ProductServiceImpl implements ProductSerice {
   @Autowired
   private ProductMapper productMapper;


    @Override
    public List<Product> selectAllProduct() {
        return productMapper.selectAllProduct();
    }

    @Override
    public Product queryById(Integer id) {
        return productMapper.queryById(id);
    }

    /**
     * @Author lilin
     * @Date 2022/5/29 16:53:57
     * @param id 商品id
     * @param num 修改的数量
     * @param username 用户名
     * @Description 修改商品数量
     */
    @Override
    public void updateNum(Integer id, Integer num, String username) {
        //查询商品是否存在
        Product result = productMapper.queryById(id);
        if (result == null)
            throw new ProductNotFoundException("商品数据不存在");

        Integer rows = productMapper.updateProductNum(id, num, username, new Date());
        //如果更新商品数量失败
        if (rows != 1)
            throw new UpdateException("更新商品数量出现未知异常");
    }

    /**
     * @Author lilin
     * @Date 2022/5/30 19:31:16
     * @param pids 商品id列表
     * @Return 返回查询到的商品数据列表
     * @Description 根据pid列表查询商品数据
     */
    @Override
    public List<Product> selectProductByPids(List<Integer> pids) {
        //如果有不存在的商品id就将其移除
        pids.removeIf(pid -> productMapper.queryById(pid) == null);
        //查询商品列表
        List<Product> result = productMapper.selectProductByPids(pids);

        //重新封装一个list数组，将Product中的无关信息剔除
        ArrayList<Product> list = new ArrayList<>();
        for (Product product: result) {
            //image、title、price、num、id
            product.setCategoryId(null);
            product.setItemType(null);
            product.setSellPoint(null);
            product.setPriority(null);
            product.setCreatedTime(null);
            product.setModifiedTime(null);
            product.setCreatedUser(null);
            product.setModifiedUser(null);
            list.add(product);
        }
        return list;
    }

    /**
     * @Author lilin
     * @Date 2022/6/3 09:36:54
     * @param type 商品类别
     * @Return 返回查询到的商品列表
     * @Description 根据商品类别查询商品
     */
    @Override
    public List<Product> selectProductByType(String type) {
        //查询商品数据
        List<Product> products = productMapper.selectProductsByType(type);

        //如果查询不到，报异常
        if (products.size() < 1)
            throw new ProductNotFoundException("商品数据查询不到");

        //重新封装数据，减少冗余
        Product product = new Product();
        List<Product> list = new ArrayList<>();
        for (Product item: products) {
            product.setId(item.getId());
            product.setImage(item.getImage());
            product.setTitle(item.getTitle());
            product.setPrice(item.getPrice());
            list.add(product);
        }
        return list;
    }
}
