package com.MrLi.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrLi on 2022/03/15/19:29
 */
//@Controller
//@ResponseBody
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle1() {
        return "Hello SpringBoot2!";
    }
}
