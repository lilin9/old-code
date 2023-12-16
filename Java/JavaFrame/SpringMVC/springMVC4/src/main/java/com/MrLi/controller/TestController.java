package com.MrLi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MrLi on 2022/02/23/13:03
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "rest";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }
}
