package com.tianmao.service;

import com.tianmao.pojo.User;

/**
 * Created by LiLin on 2022/04/10/15:33
 *
 * 用户业务模块的接口类
 */
public interface UserService {

    /**
     * @Author LiLin
     * @Date 2022/4/12 17:04
     * @Param username 用户名
     * @Param password 用户密码
     * @Description 根据用户名和密码实现登录操作
     */
    User login(String username, String password);

    /**
     * @Author LiLin
     * @Date 2022/4/10 15:34
     * @Param User 用户实体类
     * @Description 实现用户注册功能
     */
    void register(User user);

    /**
     * @Author LiLin
     * @Date 2022/5/22 17:12
     * @Param uid 用户id
     * @Param newPassword 用户新密码
     * @Param oldPassword 用户旧密码
     * @Description 根据uid更改用户密码
     */
    void updatePassword(Integer uid, String newPassword, String oldPassword);

    /**
     * @Author lilin
     * @Date 2022/6/2 17:21:16
     * @param username 用户名
     * @param newPassword 用户新密码
     * @Description 根据用户名重置密码
     */
    void resetPassword(String username, String newPassword);

    /**
     * @Author LiLin
     * @Date 2022/5/23 11:33
     * @Param uid 用户id
     * @return 返回查询到的用户信息
     * @Description 查询用户信息，根据uid
     */
    User selectUser(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/23 11:32
     * @Param uid 用户id
     * @Param username 用户名
     * @Param user 用户实体类
     * @Description 修改用户信息
     */
    void updateUserInfo(Integer uid, String username, User user);

    /**
     * @Author LiLin
     * @Date 2022/5/23 13:13
     * @Param uid 用户id
     * @Param avatar 头像地址
     * @Param username 用户名
     * @Description 根据uid更新用户头像地址
     */
    void updateAvatarByUid(Integer uid, String avatar, String username);
}
