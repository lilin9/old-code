package com.tianmao.service;

import com.tianmao.pojo.Product;

import java.util.List;

/**
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/17 21:03
 **/
public interface ProductSerice {

    /*
     * 查询全部商品
     * */
    List<Product> selectAllProduct();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);

    /**
     * @Author lilin
     * @Date 2022/5/29 16:53:57
     * @param id 商品id
     * @param num 修改的数量
     * @param username 用户名
     * @Description 修改商品数量
     */
    void updateNum(Integer id, Integer num, String username);

    /**
     * @Author lilin
     * @Date 2022/5/30 19:31:16
     * @param pids 商品id列表
     * @Return 返回查询到的商品数据列表
     * @Description 根据pid列表查询商品数据
     */
    List<Product> selectProductByPids(List<Integer> pids);

    /**
     * @Author lilin
     * @Date 2022/6/3 09:36:54
     * @param type 商品类别
     * @Return 返回查询到的商品列表
     * @Description 根据商品类别查询商品
     */
    List<Product> selectProductByType(String type);
}
