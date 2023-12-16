package com.MrLi.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MrLi on 2022/03/22/16:57
 */
@Controller
public class VieController {
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(Model model) {
        model.addAttribute("msg", "hello world");
        model.addAttribute("link", "bing.cn");
        return "success";
    }
}
