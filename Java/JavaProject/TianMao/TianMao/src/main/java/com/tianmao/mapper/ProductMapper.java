package com.tianmao.mapper;

import com.tianmao.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-17 20:57:51
 */
@Mapper
public interface ProductMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);


    /**
     * 统计总行数
     *
     * @param Product 查询条件
     * @return 总行数
     */
    long count(Product Product);

    /**
     * 新增数据
     *
     * @param Product 实例对象
     * @return 影响行数
     */
    int insert(Product Product);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Product> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Product> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Product> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Product> entities);

    /**
     * 修改数据
     *
     * @param Product 实例对象
     * @return 影响行数
     */
    int update(Product Product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /*
    * 查询全部商品
    * */
    List<Product> selectAllProduct();

    /**
     * @Author lilin
     * @Date 2022/5/29 16:49:11
     * @param id 商品id
     * @param num 商品数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @Return 返回影响的行数
     * @Description 修改商品数量
     */
    Integer updateProductNum(Integer id, Integer num, String modifiedUser, Date modifiedTime);

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
    List<Product> selectProductsByType(String type);
}

