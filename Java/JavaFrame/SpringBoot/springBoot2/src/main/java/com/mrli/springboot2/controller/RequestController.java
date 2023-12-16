package com.mrli.springboot2.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/20/8:52
 */
public class RequestController {

    @RequestMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "success…………");
        return "forward:/success";
    }

    @ResponseBody
    @RequestMapping("/success")
    public Map<String, Object> success(@RequestAttribute(value = "msg", required = false) String msg,
                                       HttpServletRequest request) {
        Object model = request.getAttribute("model");
        Object map = request.getAttribute("map");
        Object request_ = request.getAttribute("request_");
        Object response_ = request.getAttribute("response_");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("model", model);
        map1.put("map", map);
        map1.put("request_", request_);
        map1.put("response_", response_);
        map1.put("msg", msg);
        return map1;
    }

    @RequestMapping("/params")
    public String testParams(Map<String, Object> map,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        map.put("map", "this is map");
        model.addAttribute("model", "this is model");
        request.setAttribute("request_", "this is request");
        Cookie cookie = new Cookie("response_", "this is response");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:success";
    }
}
