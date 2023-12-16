package com.lilin.springboot5.controller;

import com.lilin.springboot5.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/03/30/13:44
 */
@RestController
public class HelloController {
    @Value("${person.name : temp-smith}")
    private String name;

    @Autowired
    Person person;

    @GetMapping("/")
    public String hello() {
        return person.getClass().toString();
    }
}
