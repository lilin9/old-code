package com.lilin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/14/19:38:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListDto {
    //标签名
    private String name;
    //备注
    private String remark;
}
