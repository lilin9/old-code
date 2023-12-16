package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by LiLin on 2022/9/27/15:47:59
 * 联系人详情信息 vo 类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailVo {
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;
    //账号状态（0正常 1停用）
    private String status;
    //联系人发布的职位
    List<PositionsVo> positionsVos;
}





