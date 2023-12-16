package com.lilin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.entity.ArticleTag;
import com.lilin.mapper.ArticleTagMapper;
import com.lilin.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/9/15/13:37:44
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    /**
     * @Author lilin
     * @Date 2022/9/15 13:40:31
     * @param articleTagList 博客和标签列表
     * @Return
     * @Description 添加博客和标签的关联
     */
    @Override
    public void saveBatch_(List<ArticleTag> articleTagList) {
        saveBatch(articleTagList);
    }
}
