package com.MrLi.mapper;

import com.MrLi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MrLi on 2022/03/03/17:08
 */
public interface SQLMapper {
    //根据用户名模糊查询用户信息
    User getUserByLike(@Param("username") String username);

    //批量删除用户信息
    int deleteMore(@Param("ids") String ids);

    //查询指定表中的数据
    List<User> getUserByTableName(@Param("tableName") String tableName);

    //添加用户信息
    void insertUser(User user);
}
