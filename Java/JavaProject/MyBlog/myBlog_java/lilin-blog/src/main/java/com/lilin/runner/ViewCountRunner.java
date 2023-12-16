package com.lilin.runner;

import com.lilin.constants.SystemConstants;
import com.lilin.entity.Article;
import com.lilin.mapper.ArticleMapper;
import com.lilin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/9/12/09:48:31
 * 将文章浏览信息保存到 redis 中
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) {
        //查询博客信息
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream().collect(
                Collectors.toMap((Article article1) -> article1.getId().toString(),
                        article -> article.getViewCount().intValue())
        );

        //将博客访问量存入到 redis 中去
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT, viewCountMap);
    }
}
