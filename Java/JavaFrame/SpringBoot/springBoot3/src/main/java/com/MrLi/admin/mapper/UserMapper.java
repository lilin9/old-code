package com.MrLi.admin.mapper;

import com.MrLi.admin.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by MrLi on 2022/03/28/14:55
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
