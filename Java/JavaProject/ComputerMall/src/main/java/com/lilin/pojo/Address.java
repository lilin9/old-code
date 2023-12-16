package com.lilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/04/05/9:25
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Address extends BaseEntity implements Serializable {
    private Integer aid;            //收货地址id
    private Integer uid;            //归属的用户id
    private String name;            //收货人姓名
    private String provinceName;    //省-名称
    private String provinceCode;    //省-行政代号
    private String cityName;        //市-名称
    private String cityCode;       //市-行政代号
    private String areaName;        //区-名称
    private String areaCode;        //区-行政代号
    private String zip;             //邮政编码'
    private String address;         //详细地址
    private String phone;           //手机号码
    private String tel;             //固定电话
    private String tag;             //标签
    private Integer isDefault;      //是否默认：0-不默认，1-默认
}
