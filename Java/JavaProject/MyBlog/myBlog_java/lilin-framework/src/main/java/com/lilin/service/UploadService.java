package com.lilin.service;

import com.lilin.utils.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LiLin on 2022/9/11/10:48:02
 */
public interface UploadService {
    ResponseResult<Object> uploadImg(MultipartFile img);
}
