package servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Person;

import java.io.IOException;

/**
 * Created by MrLi on 2022/01/19/16:37
 */
public class AjaxServlet extends BaseServlet {
    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到AJAX的请求了！");

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }

    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到 jQueryAjax方法 的请求了！");

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }

    protected void jQueryGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到 jQueryGet方法 的请求了！");

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }

    protected void jQueryPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到 jQueryPost方法 的请求了！");

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }

    protected void jQueryGetJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到 jQueryGetJSON方法 的请求了！");

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }

    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("阿Sir， 收到 jQuerySerialize方法 的请求了！");

        System.out.println("用户名：" + req.getParameter("username"));
        System.out.println("密  码：" + req.getParameter("password"));

        Person person = new Person("Tom", 22);
        //将person对象转换成JSON字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);

        resp.getWriter().write(personJson);
    }
}
