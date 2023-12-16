package com.lilin.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/7/6/11:40:56
 */
@RestController
public class TestController {
//    @PreAuthorize("hasAuthority('test')")
//    @PreAuthorize("hasAuthority('system:dept:list')")
    @PreAuthorize("@my.hasAuthority('system:dept:list')")
    @RequestMapping("hello")
    public String test() {
        return "Hello World";
    }
}
