package com.tianmao.mapper;

import com.tianmao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Created by LiLin on 2022/04/08/19:40
 *
 * 用户类
 */
@Mapper
public interface UserMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/8 19:43
     * @Param User 用户实体类
     * @return 返回影响的行数
     * @Description 往数据库用户表添加数据
     */
    Integer insert(User user);

    /**
     * @Author LiLin
     * @Date 2022/4/10 15:45
     * @Param username 用户名
     * @return 返回查询到的用户信息
     * @Description 根据用户名查询用户信息
     */
    User selectUserByUsername(String username);

    /**
     * @Author LiLin
     * @Date 2022/5/17 13:07
     * @Param uid 用户id
     * @return 返回查询到的用户信息
     * @Description 根据uid查询用户信息
     */
    User selectUserByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/22 17:12
     * @Param uid 用户id
     * @Param password 用户的新密码
     * @Param modifiedUser 修改人姓名
     * @Param modifiedTime 修改时间
     * @return 返回影响的行数
     * @Description 根据uid更改用户密码
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/5/22 17:12
     * @Param username 用户名
     * @Param password 用户的新密码
     * @Param modifiedUser 修改人姓名
     * @Param modifiedTime 修改时间
     * @return 返回影响的行数
     * @Description 根据username更改用户密码
     */
    Integer updatePasswordByUsername(String username, String password, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/5/23 11:13
     * @Param user 用户实体类
     * @return 返回影响的行数
     * @Description 根据uid更改用户信息
     */
    Integer updateUserInfoByUid(User user);

    /**
     * @Author LiLin
     * @Date 2022/5/23 13:06
     * @Param uid 用户id
     * @Param avatar 头像
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @return 返回影响的行数
     * @Description 更新用户头像地址
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);
}
