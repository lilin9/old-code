package com.MrLi.mapper;

import com.MrLi.pojo.User;

import java.util.List;

/**
 * Created by MrLi on 2022/03/01/14:36
 */
public interface UserMapper {
    //添加用户信息
    int insertUser();

    //删除用户信息
    int deleteUser();

    //修改用户信息
    int updateUser();

    //根据id查询用户信息
    User queryUserById();

    //查询所有用户信息
    List<User> queryUsers();
}
