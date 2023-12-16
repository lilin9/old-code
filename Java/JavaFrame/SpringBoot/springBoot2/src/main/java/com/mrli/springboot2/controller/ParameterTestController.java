package com.mrli.springboot2.controller;

import com.mrli.springboot2.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/18/15:04
 */
@RestController
public class ParameterTestController {
    @GetMapping("/PathVariable/{id}/owner/{username}")
    public Map<String, Object> testPathVariable(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        return map;
    }

    @GetMapping("/RequestHeader/{id}/owner/{username}")
    public Map<String, Object> testRequestHeader(@PathVariable("id") Integer id,
                                       @PathVariable("username") String name,
                                       @PathVariable Map<String, String> pv,
                                       @RequestHeader("User-Agent") String userAgent,
                                       @RequestHeader Map<String, Object> header) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("header", header);
        return map;
    }

    @GetMapping("/RequestParam/{id}/owner/{username}")
    public Map<String, Object> testRequestParam(@PathVariable("id") Integer id,
                                                @PathVariable("username") String name,
                                                @RequestParam("age") Integer age,
                                                @RequestParam("inters")List<String> inters,
                                                @RequestParam Map<String, String> params) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        return map;
    }

    @GetMapping("/RequestParam/{id}/owner/{username}")
    public Map<String, Object> testCookieValue(@PathVariable("id") Integer id,
                                                @PathVariable("username") String name,
                                                @CookieValue("_ga") String _ga) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("_ga", _ga);
        return map;
    }

    @PostMapping("/RequestBody")
    public Map<String, Object> testRequestBody(@RequestBody String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", content);

        return map;
    }

    /*
    1、/MatrixVariable/sell;low=34;brand=byd,audi,yd
    2、SpringBoot默认是禁用了矩阵变量的功能
              手动开启：原理是对于路径的处理，需要通过的 UrlPathHelper 的解析
                      而在UrlPathHelper里又有个 removeSemicolonContent(移除分号内容) 支持矩阵变量
    3、矩阵变量必须有url路径变量才能被解析
    */
    @RequestMapping("/MatrixVariable/{path}")
    public Map<String, Object> testMatrixVariable(@MatrixVariable("low") Integer low,
                                                  @MatrixVariable("brand") List<String> brand,
                                                  @PathVariable String path) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /MatrixVariable2/1;age=20/2;age=10
    @RequestMapping("/MatrixVariable2/{bossId}/{employeeId}")
    public Map<String, Object> testMatrixVariable2(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                                   @MatrixVariable(value = "age", pathVar = "employeeId") Integer empAge) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }

    @RequestMapping("/saveUser")
    public Person saveUser(Person person) {
        return person;
    }
}
