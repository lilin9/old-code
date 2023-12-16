package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.dto.AddArticleDto;
import com.lilin.entity.Article;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.ArticleDetailVo;
import com.lilin.vo.PageVo;

/**
 * Created by LiLin on 2022/9/3/16:03:53
 */
public interface ArticleService extends IService<Article> {
    ResponseResult<Object> getHotArticleList();

    PageVo getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ArticleDetailVo getArticleDetail(Long id);

    ResponseResult<Object> updateViewCount(Long id);

    ResponseResult add(AddArticleDto articleDto);
}
