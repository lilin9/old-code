package com.lilin.springsecurity.service.impl;

import com.lilin.springsecurity.mapper.UserMapper;
import com.lilin.springsecurity.pojo.LoginUser;
import com.lilin.springsecurity.pojo.User;
import com.lilin.springsecurity.service.UserService;
import com.lilin.springsecurity.utils.JWTUtil;
import com.lilin.springsecurity.utils.RedisCache;
import com.lilin.springsecurity.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by LiLin on 2022/7/8/12:05:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<HashMap<String, String>> saveUser(User user) {
        //先通过 AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出提示信息
        if (Objects.isNull(authentication))
            throw new RuntimeException("登录失败");

        //如果认证通过，使用 userId 生成JWT，并存入 ResponseResult 返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String uid = loginUser.getUser().getUid().toString();
        String jwt = JWTUtil.createJWT(uid);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //将完整用户信息存入 redis 中，UserId 作为key
        redisCache.setCacheObject("token:" + uid, loginUser);

        return new ResponseResult<>(200, "登录成功", map);
    }

    @Override
    public ResponseResult<Void> logout() {
        //获取 securityContextHolder 中保存的用户id
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Integer uid = loginUser.getUser().getUid();

        //根据用户id删除 redis 中保存的用户信息
        redisCache.deleteObject("login:" + uid);
        return new ResponseResult<>(200, "注销成功");
    }
}
