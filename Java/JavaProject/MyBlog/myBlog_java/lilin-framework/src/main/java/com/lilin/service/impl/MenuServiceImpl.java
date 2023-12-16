package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.mapper.MenuMapper;
import com.lilin.service.MenuService;
import com.lilin.entity.Menu;
import com.lilin.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-09-13 16:27:22
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * @Author lilin
     * @Date 2022/9/13 16:53:22
     * @param userId 用户id
     * @Return
     * @Description 获取用户对应的权限关键字
     */
    @Override
    public List<String> selectPermsByUserId(Long userId) {
        //如果是管理员，就返回所有权限
        if (Objects.equals(userId, SystemConstants.IS_USER_ADMIN)) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            //查询菜单类型为 菜单 或 按钮的数据
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            //查询菜单状态为 正常的 数据
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);

            List<Menu> menuList = list(queryWrapper);
            //获取管理员的 权限，并将其封装成 list 返回
            return menuList.stream().map(Menu::getPerms).collect(Collectors.toList());
        }

        //否则返回普通用户所具有的权限
        return getBaseMapper().selectPermsByUserId(userId);
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 10:30:14
     * @param userId 当前登录用户 id
     * @Return
     * @Description 查询用户的路由信息
     */
    @Override
    public List<Menu> getRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断当前用户是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是返回符合要求的所有 menu
            menuMapper.selectAllRouterMenu();
        } else {
            //不是，查询当前用户具有的 menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        assert menus != null;
        return buildMenuTree(menus, 0L);
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 13:52:39
     * @param menus 菜单集合
     * @param parentId 父id
     * @Return
     * @Description 将传入的 menu 集合封装成 tree 结构
     */
    private List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
        return menus.stream().filter(menu -> parentId.equals(menu.getParentId()))
                .map(menu -> menu.setChildren(getMenuChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 13:52:12
     * @param menu 菜单
     * @param menus menu的集合
     * @Return
     * @Description 获取传入的 menus 的子 menu 集合
     */
    private List<Menu> getMenuChildren(Menu menu, List<Menu> menus) {
        return menus.stream().filter(men -> men.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getMenuChildren(m, menus)))
                .collect(Collectors.toList());
    }
}

