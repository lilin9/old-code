package com.pcjp.crm.workbench.servince.impl;

import com.pcjp.crm.workbench.domain.TOrder;
import com.pcjp.crm.workbench.mapper.TOrderDao;
import com.pcjp.crm.workbench.servince.TOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * (TOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-06-04 17:21:10
 */
@Service("tOrderService")
public class TOrderServiceImpl implements TOrderService {
    @Autowired
    private TOrderDao tOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    @Override
    public TOrder queryById(String oid) {
        return this.tOrderDao.queryById(oid);
    }



    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder insert(TOrder tOrder) {
        this.tOrderDao.insert(tOrder);
        return tOrder;
    }

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
  /*  @Override
    public TOrder update(TOrder tOrder) {
        this.tOrderDao.update(tOrder);
        return this.queryById(tOrder.getOid());
    }*/

    @Override
    public int  update(TOrder tOrder) {
        return tOrderDao.update(tOrder);
    }

    @Override
    public int deleteById(String[] oid) {
        return tOrderDao.deleteById(oid);
    }

    @Override
    public List<TOrder> queryAllOrderByLimit(Map<String, Object> map) {
        return tOrderDao.queryAllOrderByLimit(map);
    }

    @Override
    public List<TOrder> queryAllOrder() {
        return tOrderDao.queryAllOrder();
    }
}
