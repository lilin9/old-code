package com.lilin;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/9/17/15:21:36
 * 将图片保存到本地
 */
public class SaveImgPipeline implements Pipeline {
    //爬取资源的保存地址
    private final static String saveUrl = "D:\\Java\\Study\\JavaReptile\\images\\";

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> imgList = (List<String>) resultItems.getAll().get("imgUrls");

        //用 stream 流将图片保存到本地
        if (!Objects.isNull(imgList))
            saveImg(imgList);
    }

    //保存图片到本地 IO流
    private void saveImg(List<String> imgUrlList) {
        InputStream is = null;
        for (String imgUrl : imgUrlList) {
            try {
                URL url = new URL(imgUrl);
                //打开连接
                URLConnection urlConnection = url.openConnection();
                //输入流
                is = urlConnection.getInputStream();
                Files.copy(is, Paths.get(saveUrl + System.currentTimeMillis() + ".jpg"));
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            assert is != null;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
