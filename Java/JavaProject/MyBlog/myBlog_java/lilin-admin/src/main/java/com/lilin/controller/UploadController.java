package com.lilin.controller;

import com.lilin.service.UploadService;
import com.lilin.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LiLin on 2022/9/15/13:25:52
 * 上传图片
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult<Object> uploadImg(@RequestParam("img") MultipartFile imgFile) {
        try {
            return uploadService.uploadImg(imgFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("图片上传失败");
        }

    }
}
