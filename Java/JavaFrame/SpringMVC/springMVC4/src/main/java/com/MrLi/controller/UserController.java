package com.MrLi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MrLi on 2022/02/23/14:58
 */
@Controller
public class UserController {
    /*
      使用RESTFUL模拟用户资源的增删改查
      /user       GET        查询所有用户信息
      /user/1     GET        根据用户id查询用户信息
      /user       POST       添加用户信息
      /user/1     DELETE     删除用户信息
      /user       PUT        修改所有用户信息
     */

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String queryUsers() {
        System.out.println("查询所有用户信息");
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String queryUserById() {
        System.out.println("根据用户id查询用户信息");
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(String username, String password) {
        System.out.println("用户名：" + username + "\n" + "密码：" + password);
        System.out.println("添加用户信息");
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(String username, String password) {
        System.out.println("用户名：" + username + "\n" + "密码：" + password);
        System.out.println("修改用户信息");
        return "success";
    }
}