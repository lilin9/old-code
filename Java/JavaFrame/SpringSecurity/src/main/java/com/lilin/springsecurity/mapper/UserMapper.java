package com.lilin.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.springsecurity.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiLin on 2022/7/7/12:01:01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
