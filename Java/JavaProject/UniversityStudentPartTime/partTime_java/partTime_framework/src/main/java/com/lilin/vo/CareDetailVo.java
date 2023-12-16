package com.lilin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/10/9/13:51:08
 * 关注表详情 vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareDetailVo {
    private Long id;
    //用户名
    private String userName;
    //被关注用户名
    private String careUserName;
    //收藏发起人0学生发起，1企业发起，2超级管理员
    private String collectUser;
    //是否被删除：0未删除，1已删除
    private String isDelete;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
