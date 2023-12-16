package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.Menu;
import com.lilin.vo.MenuVo;

import java.util.List;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-10-06 11:01:45
 */
public interface MenuService extends IService<Menu> {

    List<MenuVo> selectMenus();
}

