package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lilin.entity.Menu;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-06 11:01:44
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}

