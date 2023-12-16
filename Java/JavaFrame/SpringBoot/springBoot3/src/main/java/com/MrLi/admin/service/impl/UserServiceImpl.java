package com.MrLi.admin.service.impl;

import com.MrLi.admin.bean.User;
import com.MrLi.admin.mapper.UserMapper;
import com.MrLi.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by MrLi on 2022/03/28/15:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
