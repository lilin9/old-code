package com.lilin.controller;

import com.lilin.service.MenuService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单表(Menu)表控制层
 *
 * @author makejava
 * @since 2022-10-06 11:08:58
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    private final MenuService menuService;

    /*
    通过构造器实现依赖注入
    */
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * @Author lilin
     * @Date 2022/10/6 11:10:37
     * @Return
     * @Description 获取所有菜单数据
     */
    @GetMapping("/")
    public ResponseResult<Object> getAllMenus() {
        return ResponseResult.success(menuService.selectMenus());
    }
}

