package com.MrLi.admin.cxontroller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by MrLi on 2022/03/24/13:27
 */
@Controller
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layouts() {
        return "form/form_layouts";
    }

    /*
    MultipartFile：自动封装浏览器上传的文件
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestParam("headerImg") MultipartFile headerImg,
                         @RequestParam("photos") MultipartFile[] photos) {
        //保存到文件服务器，例如OSS服务器
        if (!headerImg.isEmpty()) headerImg.transferTo(new File("D:\\" + headerImg.getOriginalFilename()));
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (photo.isEmpty()) photo.transferTo(new File("D:\\" + photo.getOriginalFilename()));
            }
        }
        return "index";
    }
}
