package org.soft.base.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ToPage {

//    @RequestMapping("/")
    public String toLoginPage(HttpSession session){
        Object object = session.getAttribute("human");
        System.out.println("object = " + object);
        if(object != null){
            return "view/index.html";
        }else{
            return "view/login.html";
        }

    }

    @RequestMapping("/to/{path}")
    public String toViewPage(@PathVariable("path") String path){
        return "view/" + path;
    }


    @RequestMapping("/to/article/{path}")
    public String toArticlePage(@PathVariable("path") String path){
        return "article/" + path;
    }

    @RequestMapping("/to/{path}/{page}")
    public String toViewPageTo(@PathVariable("path") String path ,
                               @PathVariable("page") String page){

        return path+"/"+page;
    }

    /**
     * @Return
     * @Description TODO
     * @Author LILIN
     * @Date 2023/7/30 13:01:49
     */
    @RequestMapping("/to/show")
    public String toShow() {
        return "view/show";
    }

}
