package com.lilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/04/05/9:17
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;        //用户id
    private String username;    //用户名
    private String password;    //密码
    private String salt;        //盐值
    private String phone;       //电话号码
    private String email;       //电子邮箱
    private Integer gender;     //性别：0-女，1-男
    private String avatar;      //头像
    private Integer isDelete;   //是否删除：0-未删除，1-已删除
}
