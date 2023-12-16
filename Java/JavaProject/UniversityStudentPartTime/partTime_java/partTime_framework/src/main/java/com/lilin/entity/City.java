package com.lilin.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (City)表实体类
 *
 * @author makejava
 * @since 2022-09-24 11:13:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "city")
public class City {

    @TableId
    private Long id;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //更新人
    private String updateBy;
    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //创建人
    private String createBy;
    //城市Id
    private String cityId;
    //城市名称
    private String city;
    //省份Id
    private String provinceId;

}

