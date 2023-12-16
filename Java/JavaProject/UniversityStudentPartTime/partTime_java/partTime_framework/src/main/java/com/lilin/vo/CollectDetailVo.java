package com.lilin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/10/8/14:07:23
 * 收藏详情 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectDetailVo {
    private Long id;
    //用户名
    private String userName;
    //兼职标题
    private String jobTitle;
    //收藏发起人 0 学生发起，1 企业发起，2 管理员
    private String collectUser;
    //是否被删除0未删除，1已删除
    private String isDelete;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
