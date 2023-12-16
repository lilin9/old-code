package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * (City)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 11:13:15
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {

}

