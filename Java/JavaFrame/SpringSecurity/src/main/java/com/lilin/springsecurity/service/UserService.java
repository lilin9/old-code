package com.lilin.springsecurity.service;

import com.lilin.springsecurity.pojo.User;
import com.lilin.springsecurity.utils.ResponseResult;

import java.util.HashMap;

/**
 * Created by LiLin on 2022/7/8/12:04:00
 */
public interface UserService {
    ResponseResult<HashMap<String, String>> saveUser(User user);

    ResponseResult<Void> logout();
}
