package com.pcjp.crm.workbench.servince;


import com.pcjp.crm.workbench.domain.TUser;

import java.util.List;
import java.util.Map;


/**
 * (TUser)表服务接口
 *
 * @author makejava
 * @since 2022-05-31 08:44:03
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    TUser queryById(String uid);



    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    int  insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    int  deleteById(String [] uid);



    /**
     * 查询用户的密码是否正确
     */
    TUser selectUserByLoginActAndpwd(Map<String,Object> map);



    /**
     * 根据条件分页查询用户列表
     */
    List<TUser> queryAllByLimit(Map<String, Object> map);


    /**
     * 根据用户分页查询总条数
     */
    int queryCountOfUserByCondition(Map<String, Object> map);



    /*
     * 查询所有的用户
     * */
    List<TUser> queryallUser();


}
