package com.MrLi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MrLi on 2022/02/26/16:35
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {return "index";}

    @RequestMapping("/testInterceptor")
    public String testInterceptor() {return "success";}
}
