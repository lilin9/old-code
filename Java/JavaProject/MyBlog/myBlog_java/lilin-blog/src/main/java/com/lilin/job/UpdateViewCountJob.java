package com.lilin.job;

import com.lilin.constants.SystemConstants;
import com.lilin.entity.Article;
import com.lilin.service.ArticleService;
import com.lilin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/9/12/09:56:49
 * 定时更新数据库
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/60 * * * * ?")
    public void updateViewCount() {
        //获取 redis 中的浏览量
        Map<String, Integer> viewCount = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);
        //将 map 集合类型的数据封装成 article
        List<Article> articles = viewCount
                .entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新数据库
        articleService.updateBatchById(articles);
        System.out.println("定时任务执行成功");
    }
}
