package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.Menu;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.MenuMapper;
import com.lilin.service.MenuService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-10-06 15:29:09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    /*
    通过构造器实现依赖注入
    */
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * @Author lilin
     * @Date 2022/10/6 11:10:37
     * @Return
     * @Description 查询所有菜单数据
     */
    @Override
    public List<MenuVo> selectMenus() {
        //查询所有菜单数据
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        if (menuList.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.MENU_IS_EMPTY);

        //并且将菜单数据封装成 vo 类，并将 vo 返回
        return copyToMenuVoList(menuList);
    }

    /**
     * @param menuList 菜单列表
     * @Author lilin
     * @Date 2022/10/6 13:26:44
     * @Return
     * @Description 将 菜单列表 封装成对应的 vo 类
     */
    private List<MenuVo> copyToMenuVoList(List<Menu> menuList) {
        List<MenuVo> menuVoList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (GlobalConstant.DEFAULT_PARENT_ID.equals(menu.getParentId())) {
                MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
                menuVo.setChildren(getMenuChildren(menuVo, menuList));
                menuVoList.add(menuVo);
            }
        }
        return menuVoList;
    }

    /**
     * @param parentMenu 父 menu
     * @param menuList   原本的 menu 集合
     * @Author lilin
     * @Date 2022/10/6 13:52:13
     * @Return
     * @Description 获取 父menu 的子集
     */
    private List<MenuVo> getMenuChildren(MenuVo parentMenu, List<Menu> menuList) {
        List<MenuVo> menuVoList = new ArrayList<>();
        for (Menu menu: menuList) {
            if (parentMenu.getId().equals(menu.getParentId())) {
                MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
                menuVo.setChildren(getMenuChildren(menuVo, menuList));
                menuVoList.add(menuVo);
            }
        }
        return menuVoList;
    }
}

