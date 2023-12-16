package com.lilin.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (UserCare)表实体类
 *
 * @author makejava
 * @since 2022-09-28 13:59:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_care")
public class UserCare {

    @TableId
    private Long id;
    //用户 id
    private Long userId;
    //关注的用户 id
    private Long careId;
    //收藏发起人0学生发起，1企业发起，2超级管理员
    private String collectUser;
    //是否被删除：0未删除，1已删除
    private String isDelete;
    //创建人
    private String createBy;
    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人
    private String updateBy;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}

