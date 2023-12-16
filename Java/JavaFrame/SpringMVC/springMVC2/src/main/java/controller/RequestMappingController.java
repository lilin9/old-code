package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MrLi on 2022/02/18/15:05
 */
@Controller
public class RequestMappingController {
    @RequestMapping(value = {"/requestMappingTest", "/test", "/requestMapping"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String forwardToRequestMappingTest() {
        return "requestMappingTest";
    }

    @RequestMapping("/testPath/{id}")
    public String testPath(@PathVariable("id")Integer id) {
        System.out.println("id = " + id);
        return "requestMappingTest";
    }
}
