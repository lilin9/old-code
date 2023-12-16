package com.lilin.controller;

import com.lilin.service.UploadService;
import com.lilin.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LiLin on 2022/9/11/10:44:52
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * @Author lilin
     * @Date 2022/9/11 10:50:00
     * @param img 图片文件
     * @Return
     * @Description 上传文件
     */
    @PostMapping("/upload")
    public ResponseResult<Object> uploadImg(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
