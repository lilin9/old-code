package com.lilin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/10/6/16:52:37
 * 后台用户信息 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVo {
    private Long id;
    //昵称
    private String nickName;
    //用户名
    private String userName;
    //手机号
    private String phonenumber;
    //用户类型：0代表普通用户，1代表管理员
    private String type;
    //用户性别（0男，1女，2未知）
    private String sex;
    //邮箱
    private String email;
    //账号状态（1正常 0停用）
    private String status;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
