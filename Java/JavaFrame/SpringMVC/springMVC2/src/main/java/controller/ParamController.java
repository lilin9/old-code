package controller;

import bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by MrLi on 2022/02/19/15:54
 */
@Controller
public class ParamController {
    @RequestMapping("/servletAPI")
    public String servletAPI(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username + ", password = " + password);

        return "requestMappingTest";
    }

    @RequestMapping("/testParam")
    public String testParam(@RequestParam("user_name") String username, String password) {
        System.out.println("username = " + username + ", password = " + password);
        return "requestMappingTest";
    }

    @RequestMapping("/testParams")
    public String testParams(String username, String password, String hobby) {
        System.out.println("username = " + username + ", password = " + password + ", hobby = " + hobby);
        return "requestMappingTest";
    }

    @RequestMapping("/testBean")
    public String testBean(User user) {
        System.out.println(user);
        return "requestMappingTest";
    }
}
