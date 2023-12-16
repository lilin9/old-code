package com.MrLi.mapper;

import com.MrLi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/02/20:23
 */
public interface ParameterMapper {
    //查询所有员工信息
    List<User> queryUsers();

    //根据员工名查询员工信息
    User queryUserByUsername(String username);

    //根据用户名和用户密码查询用户信息
    User queryUserByUsernameAndPassword(String username, String password);

    //通过map查询用户信息
    User queryUserByMap(Map<String, Object> map);

    //添加用户信息
    int insertUser(User user);

    //通过@param查询用户信息
    User queryUserByParam(@Param("username")String username, @Param("password")String password);
}
