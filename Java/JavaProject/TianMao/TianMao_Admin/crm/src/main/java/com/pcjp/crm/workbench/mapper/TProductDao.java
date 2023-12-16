package com.pcjp.crm.workbench.mapper;




import com.pcjp.crm.workbench.domain.TProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (TProduct)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-02 10:41:45
 */
public interface TProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(String id);



    /**
     * 统计总行数
     *
     * @param tProduct 查询条件
     * @return 总行数
     */
    long count(TProduct tProduct);

    /**
     * 新增数据
     *
     * @param tProduct 实例对象
     * @return 影响行数
     */
    int insert(TProduct tProduct);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TProduct> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TProduct> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TProduct> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TProduct> entities);

    /**
     * 修改数据
     *
     * @param tProduct 实例对象
     * @return 影响行数
     */
    int update(TProduct tProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
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



