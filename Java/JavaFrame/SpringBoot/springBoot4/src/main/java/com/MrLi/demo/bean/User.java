package com.MrLi.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by MrLi on 2022/03/28/9:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    public String username;
    private String password;
    private String email;
}
