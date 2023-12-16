package com.MrLi.demo.mapper;

import com.MrLi.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by MrLi on 2022/03/28/9:12
 */
@Mapper
public interface UserMapper {
    User selectUsernameAndPasswordById(Integer id);
}
