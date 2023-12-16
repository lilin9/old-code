package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/7/12:12:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    //网站地址
    private String address;
    private String description;
    private Long id;
    private String logo;
    private String name;
}
