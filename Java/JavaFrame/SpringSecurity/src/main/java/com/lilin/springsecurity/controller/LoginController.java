package com.lilin.springsecurity.controller;

import com.lilin.springsecurity.pojo.User;
import com.lilin.springsecurity.service.UserService;
import com.lilin.springsecurity.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/7/8/12:01:45
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout() {
        return userService.logout();
    }
}
