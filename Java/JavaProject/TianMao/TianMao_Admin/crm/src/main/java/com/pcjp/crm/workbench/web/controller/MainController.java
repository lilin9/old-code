package com.pcjp.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MainController
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/8 14:40
 * @Version 1.0
 **/
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/shizhong/shizhong";
    }














}
