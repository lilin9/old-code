package com.pcjp.crm.workbench.mapper;


import com.pcjp.crm.workbench.domain.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (TUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-31 08:43:58
 */
public interface TUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    TUser queryById(String uid);



    /**
     * 统计总行数
     *
     * @param
     * @return 总行数
     */


    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(TUser tUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TUser> entities);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 影响行数
     */
    int deleteById(String [] uid);


    /*
    * 查询条件所有的用户
    * */
    List<TUser> selectallUser();



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
    int selectCountOfUserByCondition(Map<String, Object> map);




}

