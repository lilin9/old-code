package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/29/13:10:02
 * 用户关注 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCareDetailVo {
    private Long id;
    //头像
    private String avatar;
    //用户名
    private String userName;
    //昵称
    private String nickName;
}
