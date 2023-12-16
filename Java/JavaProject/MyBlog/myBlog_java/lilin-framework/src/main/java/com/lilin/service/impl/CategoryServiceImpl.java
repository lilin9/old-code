package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.Article;
import com.lilin.entity.Category;
import com.lilin.mapper.CategoryMapper;
import com.lilin.service.ArticleService;
import com.lilin.service.CategoryService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/9/5/10:43:35
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;
    /**
     * @Author lilin
     * @Date 2022/9/5 10:50:24
     * @Return
     * @Description 获取文章列表
     */
    @Override
    public List<CategoryVo> getCategoryList() {
        //查询状态为已发布的文章表
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLES_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);
        //获取文章分类 id，且去重
        Set<Long> categoryIds = articleList.stream().map(Article::getCategoryId).collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus())).collect(Collectors.toList());
        //封装 vo，并返回
        return CopyBeanUtils.copyBeanList(categories, CategoryVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/9/15 10:54:00
     * @Return
     * @Description 查询所有分类接口
     */
    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(queryWrapper);

        return CopyBeanUtils.copyBeanList(list, CategoryVo.class);
    }
}
