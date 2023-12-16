package com.lilin.springsecurity.expression;

import com.lilin.springsecurity.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by LiLin on 2022/7/10/16:22:16
 */
@Component("my")
public class MyExpressionRoot {
    public boolean hasAuthority(String authority) {
        //获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permission = loginUser.getPermission();

        //判断当前用户权限是否存在 authority 权限
        return permission.contains(authority);
    }
}
