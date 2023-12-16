package com.lilin;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.List;

/**
 * Created by LiLin on 2022/9/17/10:39:01
 * 设置如何爬取图片
 */
public class WallHerePageProcessor implements PageProcessor {
    //配置抓取网站的相关配置：编码、抓取间隔、重试次数等
    // 还可以设置 User Agent、cookie，以及代理的设置
    private static final Site SITE = Site.me().setRetryTimes(10).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .setTimeOut(100000);

    @Override
    public void process(Page page) {
        //定义如何抽取页面信息，并保存下来
        List<String> urlList = page.getHtml()
                .xpath("//*[@id=\"imgid\"]/div[1]/ul/li[1]/div/div[2]/a/img/@src")
                .all();

        //对 urlList 进行去重
        HashSet<String> temp = new HashSet<>(urlList);
        urlList.clear();
        urlList.addAll(temp);

        //将结果打印到控制台
        urlList.forEach(System.out::println);

        //保存到 putField 中
        page.putField("imgUrls", urlList);
    }

    @Override
    public Site getSite() {
        return SITE;
    }
}
