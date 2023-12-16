package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.entity.UserCare;
import org.apache.ibatis.annotations.Mapper;

/**
 * (UserCare)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-28 14:44:47
 */
@Mapper
public interface UserCareMapper extends BaseMapper<UserCare> {

}

