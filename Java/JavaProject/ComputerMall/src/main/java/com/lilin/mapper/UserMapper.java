package com.lilin.mapper;

import com.lilin.pojo.User;

import java.util.Date;

/**
 * Created by LiLin on 2022/04/05/10:10
 */
public interface UserMapper {

    /**
     * @author LiLin
     * @create 2022/4/5 10:10
     * @return 返回受影响的函数
     * @description 插入用户数据
     */
    Integer insert(User user);

    /**
     * @author LiLin
     * @create 2022/4/5 10:16
     * @return 如果查询成功返回用户数据，如果查询失败返回null
     * @description 根据用户名查询用户信息
     */
    User selectUserByUsername(String username);

    /**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @return 返回被影响的行数
     * @description 根据用户uid修改用户密码
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * @author LiLin
     * @create 2022/4/6 17:11
     * @return 返回user信息
     * @description 根据uid查询用户信息
     */
    User selectUserByUid(Integer uid);

    /**
     * @author LiLin
     * @create 2022/4/7 14:48
     * @return 返回用户信息
     * @description 根据uid更新用户的信息
     */
    Integer updateInfoByUid(User user);

    /**
     * @author LiLin
     * @create 2022/4/8 13:38
     * @return 返回被影响的行数
     * @description 根据uid更改头像的储存地址
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);
}
