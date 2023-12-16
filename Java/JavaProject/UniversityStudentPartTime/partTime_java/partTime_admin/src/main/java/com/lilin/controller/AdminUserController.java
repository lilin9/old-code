package com.lilin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.User;
import com.lilin.service.UserService;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.AdminUserInfoVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiLin on 2022/10/4/15:18:18
 * 后台用户控制层
 */
@RestController
@RequestMapping("user")
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @Author lilin
     * @Date 2022/10/4 15:43:28
     * @param user 用户实体类
     * @Return
     * @Description 后台系统的登录
     */
    @PostMapping("/login")
    public ResponseResult<Object> login(@RequestBody User user) {
        //先判断用户是不是管理员用户
        User resultInfo = userService.selectUserForUsername(user.getUserName());
        if (!GlobalConstant.USER_TYPE_ADMIN.equals(resultInfo.getType()))
            return ResponseResult.error(ResponseCodeEnum.USER_NOT_IS_ADMIN);

        return ResponseResult.success(userService.login(user, GlobalConstant.IS_ADMIN));
    }

    /**
     * @Author lilin
     * @Date 2022/10/6 15:45:00
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 查询用户信息
     */
    @GetMapping("/")
    public ResponseResult<Object> getUserInfos(String query, Long pageNum, Long pageSize) {
            //先根据用户名查询用户信息
            List<AdminUserInfoVo> result = userService.selectUserInfo(query, pageNum, pageSize, GlobalConstant.USERNAME);
            if (result.isEmpty())
                //如果查询结果为空，在通过昵称查询用户信息
                result = userService.selectUserInfo(query, pageNum, pageSize, GlobalConstant.NICK_NAME);

        return ResponseResult.success(result);
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 14:16:15
     * @param user 用户实体类
     * @Return
     * @Description 添加用户
     */
    @PostMapping("/")
    public ResponseResult<Object> addUser(@RequestBody User user) {
        userService.saveUserInfo(user);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 14:36:38
     * @param userId 用户 id
     * @param status 账户状态
     * @Return
     * @Description 修改账户状态：0正常 1停用
     */
    @PutMapping("/status/{userId}/{status}")
    public ResponseResult<Object> updateStatus(@PathVariable("userId") Long userId,
                                               @PathVariable("status") String status) {
        userService.updateStatus(userId, status);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 16:09:27
     * @param userId 用户 id
     * @param updateUserData 需要修改的用户实体类
     * @Return
     * @Description 修改用户数据
     */
    @PutMapping("/updateUser/{userId}")
    public ResponseResult<Object> updateUser(@PathVariable("userId") Long userId,
                                             @RequestBody JSONObject updateUserData) {
        //对JSON格式的数据进行解析，取得 CompJobInfo 实体类格式的数据
        User user = SecurityUtils.parseJsonObject(updateUserData, "updateUserData", User.class);

        //将用户 id 封装到 user 实体类中
        user.setId(userId);

        userService.updateUser(user);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 17:22:55
     * @param userId 用户 id
     * @Return
     * @Description 删除用户数据
     */
    @DeleteMapping("/{userId}")
    public ResponseResult<Object> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/12 16:50:25
     * @param token token
     * @Return
     * @Description 退出登录
     */
    @DeleteMapping("/logout")
    public ResponseResult<Object> logout(@RequestBody String token) {
        userService.logout(token);
        return ResponseResult.success();
    }
}
