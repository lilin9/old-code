package com.MrLi.admin.cxontroller;

import com.MrLi.admin.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by MrLi on 2022/03/23/9:55
 */
public class IndexController {
    @Autowired
    StringRedisTemplate redisTemplate;

    //访问登录页
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    //登录成功访问首页
    @PostMapping("/login")
    public String index(User user, HttpSession session, Model model) {
        if (!StringUtils.isEmpty(user.getUsername()) && StringUtils.hasLength(user.getPassword())) {
            session.setAttribute("loginUser", user);
            //重定向防止表单重复提交
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }

    //跳转到首页
    @GetMapping("/index.html")
    public String indexPage(Model model) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String index = opsForValue.get("/index.html");
        String sql = opsForValue.get("/sql");

        if (index != null && !"".equals(index)) model.addAttribute("index", index);
        if (sql != null && !"".equals(sql)) model.addAttribute("sql", sql);

        return "index";
    }
}
