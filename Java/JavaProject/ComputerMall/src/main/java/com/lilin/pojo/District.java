package com.lilin.pojo;

import com.lilin.controller.BaseController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/04/12/14:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District extends BaseController {
    private Integer id;
    private String parent;  //父区域的代码号（省的父代码号是 +86）
    private String code;
    private String name;
}
