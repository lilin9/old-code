package com.pcjp.crm.workbench.servince;


import com.pcjp.crm.workbench.domain.TOrder;

import java.util.List;
import java.util.Map;

/**
 * (TOrder)表服务接口
 *
 * @author makejava
 * @since 2022-06-04 14:03:16
 */
public interface TOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrder queryById(String oid);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder insert(TOrder tOrder);


    int  update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 是否成功
     */
    int deleteById(String [] oid);


    /**
     * 查询全部订单
     */
    List<TOrder> queryAllOrder();


    /**
     * 分页查询
     */
    List<TOrder> queryAllOrderByLimit(Map<String,Object> map);
}
