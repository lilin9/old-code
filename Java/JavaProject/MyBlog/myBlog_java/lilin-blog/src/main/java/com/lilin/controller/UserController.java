package com.lilin.controller;

import com.lilin.entity.User;
import com.lilin.service.UserService;
import com.lilin.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiLin on 2022/9/10/13:25:26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @Author lilin
     * @Date 2022/9/10 13:27:27
     * @Return
     * @Description 获取用户信息
     */
    @GetMapping("/userInfo")
    public ResponseResult<Object> userInfo() {
        return userService.userInfo();
    }

    /**
     * @Author lilin
     * @Date 2022/9/11 13:52:52
     * @param user 用户实体类
     * @Return
     * @Description 更新用户信息
     */
    @PutMapping("/userInfo")
    public ResponseResult<Object> updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * @Author lilin
     * @Date 2022/9/11 14:26:50
     * @param user 用户实体类
     * @Return
     * @Description 注册
     */
    @PostMapping("/register")
    public ResponseResult<Object> register(@RequestBody User user) {
        return userService.register(user);
    }
}
