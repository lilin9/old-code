package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.User;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.BlogUserLoginVo;

/**
 * Created by LiLin on 2022/9/5/10:43:02
 */
public interface UserService extends IService<User> {
    BlogUserLoginVo login(User user);

    void logout();

    ResponseResult<Object> userInfo();

    ResponseResult<Object> updateUserInfo(User user);

    ResponseResult<Object> register(User user);
}
