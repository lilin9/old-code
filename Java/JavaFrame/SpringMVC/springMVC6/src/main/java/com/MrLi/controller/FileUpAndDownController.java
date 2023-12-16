package com.MrLi.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by MrLi on 2022/02/26/14:12
 */
@Controller
public class FileUpAndDownController {
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws Exception {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/images/1.jpg");
        //创建输入流
        FileInputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载的方式和下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=好看的二次元妹子.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //获取上传文件的文件名
        String originalFilename = photo.getOriginalFilename();
        //获取上传的文件后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //获取 uuid
        String uuid = UUID.randomUUID().toString();
        //将 uuid 和 后缀名 拼接后的结果作为最终的文件名
        originalFilename = uuid + suffixName;
        //通过 ServletContext 获取服务器中photo目录的路径
        ServletContext context = session.getServletContext();
        String photoPath = context.getRealPath("photo");
        File file = new File(photoPath);
        //判断photoPath所对应的路径是否存在
        if (!file.exists()) {
            //如果不存在，就创建目录
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + originalFilename;
        photo.transferTo(new File(finalPath));
        return "success";
    }
}