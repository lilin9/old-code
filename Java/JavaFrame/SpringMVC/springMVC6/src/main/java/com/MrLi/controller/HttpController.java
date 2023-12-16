package com.MrLi.controller;

import com.MrLi.bean.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by MrLi on 2022/02/25/15:31
 */
@Controller
public class HttpController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/file")
    public String file() {
        return "file";
    }

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("requestBody = " + requestBody);
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        //当前 requestEntity 表示整个请求报文的信息
        System.out.println("requestHeader: " + requestEntity.getHeaders());
        System.out.println("requestBody: " + requestEntity.getBody());
        return "success";
    }

    @RequestMapping("/testResponseServletAPI")
    public void testResponseServletAPI(HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello World!");
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody() {
        return "success";
    }

    @RequestMapping("/testResponseBodyUser")
    @ResponseBody
    public User testResponseBodyUser() {
        return new User(101, "tom", "man", 12);
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username, String password) {
        System.out.println(username + ", " + password);
        return "success, axios";
    }
}
