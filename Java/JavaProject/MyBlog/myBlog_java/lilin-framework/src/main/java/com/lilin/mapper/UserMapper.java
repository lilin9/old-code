package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiLin on 2022/9/5/10:42:22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
