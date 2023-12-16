package com.lilin.service;

import com.lilin.entity.ArticleTag;

import java.util.List;

/**
 * Created by LiLin on 2022/9/15/13:37:09
 */
public interface ArticleTagService {
    void saveBatch_(List<ArticleTag> articleTagList);
}
