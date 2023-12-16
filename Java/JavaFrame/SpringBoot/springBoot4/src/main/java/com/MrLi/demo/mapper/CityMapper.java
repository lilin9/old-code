package com.MrLi.demo.mapper;

import com.MrLi.demo.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by MrLi on 2022/03/28/11:22
 */
@Mapper
public interface CityMapper {
    @Select("select 'name','state','country' from city where id=#{id}")
    City selectAllById(Integer id);

    @Insert("insert into city(`name`,state,country) values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertCity(City city);
}
