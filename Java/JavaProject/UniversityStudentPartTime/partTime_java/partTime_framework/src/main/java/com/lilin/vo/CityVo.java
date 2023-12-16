package com.lilin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/10/9/16:49:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityVo {
    private Long id;
    //城市Id
    private String cityId;
    //城市名称
    private String city;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
