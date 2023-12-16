package com.lilin.service;

import com.lilin.pojo.User;

/**
 * Created by LiLin on 2022/04/05/14:03
 *
 * 用户业务层接口
 */
public interface UserService {

    /**
     * @author LiLin
     * @create 2022/4/5 14:04
     * @return 如果返回值为null说明登录失败；如果返回用户信息说明登录成功
     * @description 根据用户名和密码实现登录操作
     */
    User login(String username, String password);

    /**
     * @author LiLin
     * @create 2022/4/5 14:06
     * @description 实现用户注册操作
     */
    void register(User user);

    /**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @description 根据用户uid修改用户密码
     */
    void updatePasswordByUid(Integer uid,
                                String username,
                                String newPassword,
                                String oldPassword);

    /**
     * @author LiLin
     * @create 2022/4/7 15:27
     * @return 返回用户的信息
     * @description 根据uid查询用户信息
     */
    User getUserByUid(Integer uid);


    /**
     * @author LiLin
     * @create 2022/4/7 15:43
     * @description 根据uid更新用户信息
     */
    void updateInfo(Integer uid, String username , User user);

    /**
     * @author LiLin
     * @create 2022/4/8 14:11
     * @description 根据用户uid更新用户头像
     */
    void updateAvatar(Integer uid, String avatar, String username);
}
