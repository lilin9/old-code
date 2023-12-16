package com.MrLi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by MrLi on 2022/02/27/14:05
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public String TestException(Exception e, Model model) {
        model.addAttribute("e", e);
        return "error";
    }
}
