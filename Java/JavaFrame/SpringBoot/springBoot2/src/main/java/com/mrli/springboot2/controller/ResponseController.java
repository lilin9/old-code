package com.mrli.springboot2.controller;

import com.mrli.springboot2.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by MrLi on 2022/03/21/13:07
 */
@Controller
public class ResponseController {

    /*
    1、浏览器发送请求直接返回xml                  【application/xml】      jacksonXmlConverter
    2、如果是ajax请求，返回json                  【application/json】     jacksonJsonConverter
    3、如果guiguApp发送请求，返回自定义协议数据     【application/x-guigu】   xxxConverter

    解决步骤：
        1、添加自定义的 MessageConverter 进入系统底层
        2、系统底层会统计出所有的 MessageConverter 可以操作的数据
        3、客户端内容协商：guigu ---> guigu
     */
    @ResponseBody //利用返回值处理器里面的消息转换器进行处理
    @GetMapping("/person")
    public Person getPerson() {
        Person person = new Person();
        person.setUsername("smith");
        person.setAge(18);
        person.setBirth(new Date());
        return person;
    }
}