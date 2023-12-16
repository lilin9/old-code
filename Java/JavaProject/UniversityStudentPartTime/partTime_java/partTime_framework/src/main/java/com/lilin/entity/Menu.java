package com.lilin.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单表(Menu)表实体类
 *
 * @author makejava
 * @since 2022-10-06 15:22:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    //菜单表id
    private Long id;
    //父菜单id，一级菜单id为0
    private Long parentId;
    //菜单名字
    private String name;
    //点击菜单会跳转的路径
    private String path;
    //创建人
    private String createBy;
    //创建时间
    private Date createTime;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;

}

