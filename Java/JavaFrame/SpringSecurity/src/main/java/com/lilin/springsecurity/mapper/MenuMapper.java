package com.lilin.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.springsecurity.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LiLin on 2022/7/9/17:06:46
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userId);
}
