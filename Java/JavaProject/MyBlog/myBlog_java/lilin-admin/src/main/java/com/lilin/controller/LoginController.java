package com.lilin.controller;

import com.lilin.constants.SystemConstants;
import com.lilin.entity.User;
import com.lilin.entity.Menu;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.service.LoginService;
import com.lilin.service.MenuService;
import com.lilin.service.RoleService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.utils.RedisCache;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.AdminUserInfoVo;
import com.lilin.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/9/7/13:47:15
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * @Author lilin
     * @Date 2022/9/7 13:51:26
     * @param user 用户信息
     * @Return
     * @Description 登录接口
     */
    @PostMapping("/user/login")
    public ResponseResult<Object> login(@RequestBody User user) {
        //异常信息判断
        if (!StringUtils.hasText(user.getUsername()))
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);

        return loginService.login(user);
    }

    /**
     * @Author lilin
     * @Date 2022/9/13 16:52:22
     * @Return
     * @Description 获取后台用户信息
     */
    @GetMapping("/getInfo")
    public ResponseResult<Object> getInfo() {
        //获取当前登录的用户
        User user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getId();

        //根据用户 id 查询权限信息
        List<String> perms = menuService.selectPermsByUserId(userId);

        //根据用户 id 查询角色信息
        List<String> roles = roleService.selectRolesByUserId(userId);

        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roles, CopyBeanUtils.copyBean(user, UserInfoVo.class));
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<Object> getRouters() {
        //查询当前登录用户id
        User user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getId();
        //查询 menu，结果是 tree 的形式
        List<Menu> menus = menuService.getRouterMenuTreeByUserId(userId);
        //封装数据并且返回
        return ResponseResult.okResult(menus);
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 15:48:05
     * @Return
     * @Description 退出登录
     */
    @PostMapping("/user/logout")
    public ResponseResult logout() {
        loginService.logout();
        return ResponseResult.okResult();
    }
}
