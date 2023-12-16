package com.lilin.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lilin.springsecurity.mapper.MenuMapper;
import com.lilin.springsecurity.mapper.UserMapper;
import com.lilin.springsecurity.pojo.LoginUser;
import com.lilin.springsecurity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/7/7/13:50:50
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);

        //确保查询到的用户不为空
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        //查询对应的权限信息
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getUid().longValue());

        //将数据封装成UserDetails数据返回
        return new LoginUser(user, list);
    }
}
