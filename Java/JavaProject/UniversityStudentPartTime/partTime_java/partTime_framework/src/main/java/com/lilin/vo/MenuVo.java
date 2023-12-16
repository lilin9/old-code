package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by LiLin on 2022/10/6/11:11:08
 * 菜单 vo 类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {
    //菜单表id
    private Long id;
    //菜单名字
    private String name;
    //点击菜单会跳转的路径
    private String path;
    //子菜单
    private List<MenuVo> children;
}
