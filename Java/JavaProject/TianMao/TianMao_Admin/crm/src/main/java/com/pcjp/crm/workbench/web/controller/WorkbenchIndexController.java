package com.pcjp.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName WorkbenchIndexController
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/4 20:53
 * @Version 1.0
 **/
@Controller
public class WorkbenchIndexController {

    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }
}
