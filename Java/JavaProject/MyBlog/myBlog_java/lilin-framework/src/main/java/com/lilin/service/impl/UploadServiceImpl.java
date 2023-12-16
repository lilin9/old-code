package com.lilin.service.impl;

import com.google.gson.Gson;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.service.UploadService;
import com.lilin.utils.PathUtils;
import com.lilin.utils.ResponseResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by LiLin on 2022/9/11/10:49:44
 */
@Data
@Service
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domainName;

    /**
     * @Author lilin
     * @Date 2022/9/11 10:50:00
     * @param img 图片文件
     * @Return
     * @Description 上传文件
     */
    @Override
    public ResponseResult<Object> uploadImg(MultipartFile img) {
        //判断文件类型或者文件大小
        //获取原始文件类型
        String filename = img.getOriginalFilename();
        //对原始文件类型进行判断
        assert filename != null;
        if (!filename.endsWith(".jpg") && !filename.endsWith(".png"))
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);

        //判断通过，上传文件到 OSS
        return ResponseResult.okResult(uploadOss(img, PathUtils.generateFilePath(filename)));
    }

    private String uploadOss(MultipartFile img, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //不指定 key 的情况下，以该文件内容的 hash 值作为 key
        try {
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, filePath, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://" + domainName + filePath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return null;
    }
}
