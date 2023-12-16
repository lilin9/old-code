package com.MrLi.admin.cxontroller;

import com.MrLi.admin.bean.User;
import com.MrLi.admin.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by MrLi on 2022/03/23/12:42
 */
@Controller
public class TableController {
    @Autowired
    UserService userService;

    //@GetMapping("/basic_table")
    public String basic_table() {
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                Model model) {

        //从数据库中查询到数据进行展示
        //List<User> users = userService.list();

        //查询分页数据
        Page<User> userPage = new Page<>(pn, 2);
        IPage<User> page = userService.page(userPage);

        //model.addAttribute("users", users);
        model.addAttribute("page", page);

        return "table/dynamic_table";
    }

   @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }

   @GetMapping("/pricing_table")
    public String pricing_table() {
        return "table/pricing_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id,
                                 @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                 RedirectAttributes ra) {
        userService.removeById(id);
        ra.addAttribute("pn", pn);
        return "redirect:/dynamic_table";
    }
}
