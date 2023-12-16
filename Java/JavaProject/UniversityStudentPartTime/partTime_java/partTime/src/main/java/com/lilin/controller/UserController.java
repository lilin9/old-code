package com.lilin.controller;

import com.lilin.constant.GlobalConstant;
import com.lilin.entity.User;
import com.lilin.service.UserService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2022-09-20 14:00:48
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @Author lilin
     * @Date 2022/9/20 14:44:14
     * @param user 用户实体
     * @Return
     * @Description 注册用户
     */
    @PostMapping("/register")
    public ResponseResult<Object> register(@RequestBody User user) {
        userService.saveUserInfo(user);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/9/21 16:43:03
     * @param user 用户实体类
     * @Return
     * @Description 登录
     */
    @PostMapping("/login")
    public ResponseResult<Object> login(@RequestBody User user) {
        return ResponseResult.success(userService.login(user, GlobalConstant.NO_ADMIN));
    }

    /**
     * @Author lilin
     * @Date 2022/9/27 15:46:18
     * @param userId 用户 id
     * @Return
     * @Description 获取用户详情信息
     */
    @PostMapping("/userDetail/{userId}")
    public ResponseResult<Object> userDetail(@PathVariable Long userId) {
        return ResponseResult.success(userService.selectUserDetail(userId));
    }
}

