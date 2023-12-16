package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.annotation.SystemLog;
import com.lilin.entity.User;
import com.lilin.utils.ResponseResult;

/**
 * Created by LiLin on 2022/9/13/14:33:57
 */
public interface LoginService extends IService<User> {
    ResponseResult<Object> login(User user);

    @SystemLog(businessName = "更新用户信息")
    ResponseResult<Object> userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    void logout();
}
