package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.LoginUser;
import com.lilin.entity.User;
import com.lilin.mapper.UserMapper;
import com.lilin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/9/7/14:18:06
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);

        //判断查询是否成功
        if (Objects.isNull(user))
            throw new RuntimeException("用户不存在");

        //如果是后台用户才需要查询权限封装
        if (SystemConstants.IS_USER_ADMIN.equals(Long.getLong(user.getType()))) {
            List<String> list = menuService.selectPermsByUserId(user.getId());
            return new LoginUser(user, list);
        }
        //将查询结果返回
        return new LoginUser(user);
    }
}
