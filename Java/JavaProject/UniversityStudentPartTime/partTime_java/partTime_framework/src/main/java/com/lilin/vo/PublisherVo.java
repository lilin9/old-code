package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/24/16:38:44
 * 联系人 vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherVo {
    //用户 id
    private Long id;
    //联系人头像
    private String avatar;
    //昵称
    private String nickName;
    //用户名
    private String userName;
}









