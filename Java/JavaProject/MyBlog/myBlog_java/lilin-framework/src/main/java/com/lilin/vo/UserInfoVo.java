package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/7/14:50:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    //头像
    private String avatar;
    //邮箱
    private String email;
    //主键
    private Long id;
    //昵称
    private String nickName;
    //用户性别（0男，1女，2未知）
    private String sex;
}
