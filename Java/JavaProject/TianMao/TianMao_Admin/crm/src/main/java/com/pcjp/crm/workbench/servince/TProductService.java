package com.pcjp.crm.workbench.servince;


import com.pcjp.crm.workbench.domain.TProduct;

import java.util.List;
import java.util.Map;


/**
 * (TProduct)表服务接口
 *
 * @author makejava
 * @since 2022-06-02 10:41:48
 */
public interface TProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(String  id);


    /**
     * 新增数据
     *
     * @param tProduct 实例对象
     * @return 实例对象
     */
    TProduct insert(TProduct tProduct);

    /**
     * 修改数据
     *
     * @param tProduct 实例对象
     * @return 实例对象
     */
    int update(TProduct tProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(String []id);






    /**
     * 根据条件分页查询所有商品列表
     */
    List<TProduct> queryAllProductByLimit(Map<String, Object> map);

    /**
     * 增加指定列数
     */
    int insertProduct(TProduct tProduct);


    /**
     * 查询所有商品的总数
     */
    List<TProduct> queryAllProduct();

}
