package com.pcjp.crm.workbench.servince.impl;


import com.pcjp.crm.workbench.domain.TUser;
import com.pcjp.crm.workbench.mapper.TUserDao;
import com.pcjp.crm.workbench.servince.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2022-05-31 08:44:04
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao tUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public TUser queryById(String uid) {
        return this.tUserDao.queryById(uid);
    }


    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TUser tUser) {
        this.tUserDao.insert(tUser);
        return tUserDao.insert(tUser);
    }

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    /*@Override
    public TUser update(TUser tUser) {
        this.tUserDao.update(tUser);
        return this.queryById(tUser.getUid());
    }*/


    @Override
    public int update(TUser tUser) {
        return tUserDao.update(tUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public int  deleteById(String [] uid) {
        return tUserDao.deleteById(uid);
    }


    @Override
    public TUser selectUserByLoginActAndpwd(Map<String, Object> map) {
        return tUserDao.selectUserByLoginActAndpwd(map);
    }

    /**
     * 根据条件分页查询用户列表
     */
    @Override
    public List<TUser> queryAllByLimit(Map<String, Object> map) {
        return tUserDao.queryAllByLimit(map);
    }

    /**
     * 根据用户分页查询总条数
     */
    @Override
    public int queryCountOfUserByCondition(Map<String, Object> map) {
        return tUserDao.selectCountOfUserByCondition(map);
    }

    @Override
    public List<TUser> queryallUser() {
        return tUserDao.selectallUser();
    }


}
