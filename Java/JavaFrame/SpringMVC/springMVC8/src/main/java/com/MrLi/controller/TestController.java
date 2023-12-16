package com.MrLi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MrLi on 2022/02/27/16:10
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
