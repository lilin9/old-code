package com.lilin.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/7/9/17:02:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
    private static final long serialversionUID = -549041104113736L;
    @TableId
    private Long id;
    private String menuName;        //菜单名
    private String path;            //路由地址
    private String component;       //组件路径
    private String visible;         //菜单状态（0显示，1隐藏）
    private String status;          //菜单状态（0正常，1停用）
    private String perms;           //权限标识
    private String icon;            //菜单图标
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer delFlag;        //是否删除（0未删除，1已删除）
    private String remark;          //备注
}
