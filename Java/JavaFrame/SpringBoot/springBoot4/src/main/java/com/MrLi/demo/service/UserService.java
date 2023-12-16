package com.MrLi.demo.service;

import com.MrLi.demo.bean.User;
import com.MrLi.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MrLi on 2022/03/28/9:39
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUsernameAndPasswordById(Integer id) {
        if (id != 0) {
            return userMapper.selectUsernameAndPasswordById(id);
        }
        return null;
    }
}
