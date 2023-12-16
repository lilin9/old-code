package com.MrLi.demo.controller;

import com.MrLi.demo.bean.City;
import com.MrLi.demo.service.CityService;
import com.MrLi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MrLi on 2022/03/28/9:41
 */
@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;

    @ResponseBody
    @GetMapping("/getCity")
    public String getAllById(@RequestParam("id") Integer id) {
        return cityService.selectAllById(id).toString();
    }

    @ResponseBody
    @GetMapping("/getUser")
    public String getUsernameAndPasswordById(@RequestParam("id") Integer id) {
        return userService.getUsernameAndPasswordById(id).toString();
    }

    @ResponseBody
    @PostMapping("/insert")
    public int insertCity(City city) {
        return cityService.insertCity(city);
    }
}
