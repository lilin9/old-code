package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/15/10:59:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    public Long id;
    //标签名
    public String name;
}
