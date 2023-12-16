package com.pcjp.crm.workbench.servince.impl;


import com.pcjp.crm.workbench.domain.TProduct;
import com.pcjp.crm.workbench.mapper.TProductDao;
import com.pcjp.crm.workbench.servince.TProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * (TProduct)表服务实现类
 *
 * @author makejava
 * @since 2022-06-02 10:41:49
 */
@Service("tProductService")
public class TProductServiceImpl implements TProductService {
    @Autowired
    private TProductDao tProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProduct queryById(String  id) {
        return tProductDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param tProduct 实例对象
     * @return 实例对象
     */
    @Override
    public TProduct insert(TProduct tProduct) {
        this.tProductDao.insert(tProduct);
        return tProduct;
    }


    @Override
    public int update(TProduct tProduct) {
        return tProductDao.update(tProduct);
    }

    @Override
    public List<TProduct> queryAllProductByLimit(Map<String, Object> map) {
        return tProductDao.queryAllProductByLimit(map);
    }

    @Override
    public int insertProduct(TProduct tProduct) {
        return tProductDao.insertProduct(tProduct);
    }

    @Override
    public int deleteById(String[] id) {
        return tProductDao.deleteById(id);
    }


    @Override
    public List<TProduct> queryAllProduct() {
        return tProductDao.queryAllProduct();
    }
}
