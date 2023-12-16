package com.lilin.controller;

import com.lilin.entity.User;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.service.UserService;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.BlogUserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/9/7/13:47:15
 */
@RestController
public class LoginUserController {
    @Autowired
    private UserService userService;

    /**
     * @Author lilin
     * @Date 2022/9/7 13:51:26
     * @param user 用户信息
     * @Return
     * @Description 登录接口
     */
    @PostMapping("login")
    public ResponseResult<Object> login(@RequestBody User user) {
        //异常信息判断
        if (!StringUtils.hasText(user.getUsername()))
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);

        BlogUserLoginVo blogUserLoginVo = userService.login(user);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    /**
     * @Author lilin
     * @Date 2022/9/8 14:58:36
     * @Return
     * @Description 退出登录
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        userService.logout();
        return ResponseResult.okResult();
    }
}
