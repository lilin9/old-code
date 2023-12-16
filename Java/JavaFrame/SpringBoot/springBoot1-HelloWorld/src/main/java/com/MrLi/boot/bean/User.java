package com.MrLi.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by MrLi on 2022/03/16/12:33
 */
@Data //set、get方法
@ToString //toString方法
@NoArgsConstructor //无参构造器
@AllArgsConstructor //有参构造器
public class User {
    private Integer id;
    private String username;
}
