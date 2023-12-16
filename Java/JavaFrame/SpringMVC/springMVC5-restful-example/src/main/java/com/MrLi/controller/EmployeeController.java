package com.MrLi.controller;

import com.MrLi.bean.Employee;
import com.MrLi.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrLi on 2022/02/23/17:41
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/employee")
    public ModelAndView getAllEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employeeList", employeeDao.getAll());
        modelAndView.setViewName("employee_list");
        return modelAndView;
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/employee";
    }

    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeDao.get(id));
        return "employee_update";
    }

    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employee";
    }
}
