package com.lilin.controller;

import com.lilin.constant.GlobalConstant;
import com.lilin.service.UserCareService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiLin on 2022/10/6/15:52:18
 */
@RestController
@RequestMapping("userCare")
public class UserCareController {
    private final UserCareService userCareService;

    public UserCareController(UserCareService userCareService) {
        this.userCareService = userCareService;
    }

    /**
     * @Author lilin
     * @Date 2022/9/28 14:02:28
     * @param token token
     * @Return
     * @Description 获取登录用户关注的用户 id 列表
     */
    @PostMapping("/care")
    public ResponseResult<Object> userCare(@RequestBody String token) {
        return ResponseResult.success(userCareService.getUserCares(token));
    }

    /**
     * @Author lilin
     * @Date 2022/9/28 16:23:11
     * @param careId 要关注的用户 id
     * @param token token
     * @Return
     * @Description 关注用户
     */
    @PutMapping("/toCare/{id}")
    public ResponseResult<Object> toCare(@PathVariable("id") Long careId, @RequestBody String token) {
        userCareService.isCare(careId, token, GlobalConstant.NO_DELETE);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 08:58:29
     * @param careId 要关注的用户 id
     * @param token token
     * @Return
     * @Description 取消关注用户
     */
    @DeleteMapping("/cancelCare/{id}")
    public ResponseResult<Object> cancelCare(@PathVariable("id") Long careId, @RequestBody String token) {
        userCareService.isCare(careId, token, GlobalConstant.IS_DELETE);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 13:12:22
     * @param token token
     * @Return
     * @Description 获取登录用户关注的用户列表
     */
    @GetMapping("/userCareDetail/{token}")
    public ResponseResult<Object> userCareDetails(@PathVariable String token) {
        return ResponseResult.success(userCareService.getUserCareDetails(token));
    }
}
