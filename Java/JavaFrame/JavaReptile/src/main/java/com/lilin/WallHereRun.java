package com.lilin;

import us.codecraft.webmagic.Spider;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LiLin on 2022/9/17/13:13:27
 * 启动程序
 */
public class WallHereRun {
    //爬取网络地址
    private final static String url = "https://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&dyTabStr=MCwzLDYsMSw0LDUsOCw3LDIsOQ%3D%3D&word=%E7%8C%AB";

    public static void main(String[] args) {
        //要抓取的页面链接
        Spider spider = new Spider(new WallHerePageProcessor());
        spider
                //开启 5 个线程同时执行，数量不能小于 2
                .thread(5)
                //定义请求方式
                .addUrl(url)
                .addPipeline(new SaveImgPipeline())
                //启动
                .run();

        //定义一个定时器，一定时间后终止程序
//        Timer timer = new Timer();
//        timer.schedule(stop(spider), 5000);
    }

    private static TimerTask stop(Spider spider) {
        return new TimerTask() {
            @Override
            public void run() {
                spider.stop();
            }
        };
    }

}
