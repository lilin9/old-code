package com.MrLi.mapper;

import com.MrLi.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/03/16:00
 */
public interface SelectMapper {
    //根据id查询用户信息
    User getUserById(@Param("id") Integer id);

    //查询所有用户信息
    List<User> getAllUser();

    //查询用户信息总记录数
    Integer getUserCount();

    //根据用户id查询用户信息到一个map集合中
    Map<String, Object> getUserByIdToMap(@Param("id")Integer id);

    //查询所有用户信息到一个map集合中
    //List<Map<String, Object>> getAllUserToMap();
    @MapKey("id")
    Map<String, Object> getAllUserToMap();
}
