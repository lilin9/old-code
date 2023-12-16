package com.lilin.controller;

import com.lilin.dto.AddArticleDto;
import com.lilin.service.ArticleService;
import com.lilin.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiLin on 2022/9/15/10:27:31
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * @Author lilin
     * @Date 2022/9/15 10:34:18
     * @param articleDto articleDto实体类
     * @Return
     * @Description 添加文章
     */
    @PostMapping
    public ResponseResult<Object> add(AddArticleDto articleDto) {
        return articleService.add(articleDto);
    }

}
