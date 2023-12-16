package com.pcjp.crm.workbench.mapper;


import com.pcjp.crm.workbench.domain.TOrder;

import java.util.List;
import java.util.Map;

/**
 * (TOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-04 14:03:15
 */
public interface TOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrder queryById(String oid);


    /**
     * 统计总行数
     *
     * @param tOrder 查询条件
     * @return 总行数
     */
    long count(TOrder tOrder);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 影响行数
     */
    int insert(TOrder tOrder);


    int update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 影响行数
     */
    int deleteById(String [] oid);



    /**
     * 分页查询
     */
    List<TOrder> queryAllOrderByLimit(Map<String,Object> map);

    /**
     * 查询全部订单
     */
    List<TOrder> queryAllOrder();

}

