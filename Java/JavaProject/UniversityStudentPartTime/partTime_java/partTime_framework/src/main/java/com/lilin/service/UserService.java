package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.User;
import com.lilin.vo.AdminUserInfoVo;
import com.lilin.vo.ContactDetailVo;
import com.lilin.vo.LoginUserVo;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-09-20 14:00:50
 */
public interface UserService extends IService<User> {

    void saveUserInfo(User user);

    LoginUserVo login(User user, String isAdmin);

    User selectUserForUsername(String username);

    ContactDetailVo selectUserDetail(Long userId);

    List<AdminUserInfoVo> selectUserInfo(String query, Long pageNum, Long pageSize, String column);

    void updateStatus(Long userId, String status);

    void updateUser(User updateUserData);

    void deleteUser(Long userId);

    void logout(String token);
}

