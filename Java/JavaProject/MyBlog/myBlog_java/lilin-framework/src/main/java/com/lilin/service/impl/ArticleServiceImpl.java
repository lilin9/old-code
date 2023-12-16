package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.dto.AddArticleDto;
import com.lilin.entity.Article;
import com.lilin.entity.ArticleTag;
import com.lilin.entity.Category;
import com.lilin.mapper.ArticleMapper;
import com.lilin.service.ArticleService;
import com.lilin.service.ArticleTagService;
import com.lilin.service.CategoryService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.utils.RedisCache;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.ArticleDetailVo;
import com.lilin.vo.ArticleListVo;
import com.lilin.vo.HotArticleVo;
import com.lilin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/9/3/16:04:31
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleTagService articleTagService;

    /**
     * @Author lilin
     * @Date 2022/9/4 15:11:46
     * @Return 返回封装了热门文章列表的 ResponseResult
     * @Description 获取热门文章列表
     */
    @Override
    public ResponseResult<Object> getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLES_STATUS_NORMAL);
        //按照浏览量进行降序排序
        wrapper.orderByDesc(Article::getViewCount);
        //最多只查询 10 条
        Page<Article> page = new Page<>(1, 10);
        page(page, wrapper);
        //查询数据
        List<Article> articles = page.getRecords();
        //进行 bean 拷贝，减少数据传输流量
        List<HotArticleVo> articleVos = CopyBeanUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    /**
     * @param pageNum    分页数量
     * @param pageSize   每页的大小
     * @param categoryId 文章 id
     * @Author lilin
     * @Date 2022/9/5 15:22:10
     * @Return
     * @Description 查询文章内容
     */
    @Override
    public PageVo getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //判断 categoryId 是否存在
        queryWrapper.eq(Objects.nonNull (categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        //判断文章状态是否是发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLES_STATUS_NORMAL);
        //根据 isTop 进行降序排序
        queryWrapper.orderByDesc(Article::getIsTop);
        //进行分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //查询 categoryName
       List<Article> articles =  page.getRecords();
       //通过 articleId 去查询 articleName
        for (Article article: articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        //封装成 vo
        List<ArticleListVo> articleListVoList = CopyBeanUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        //返回 pageVo
        return new PageVo(articleListVoList, page.getTotal());
    }

    /**
     * @Author lilin
     * @Date 2022/9/6 15:14:03
     * @param id 文章 id
     * @Return
     * @Description 查询文章详情
     */
    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        //根据 id 查询文章
        Article article = getById(id);
        //从 redis 中查询文章浏览量
        Integer articleViewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString());
        //修改 article 实体类中的文章浏览量
        article.setViewCount(Long.valueOf(articleViewCount));
        //转换成 vo
        ArticleDetailVo articleDetailVo = CopyBeanUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类 id 查询分类名
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if (category != null)
            articleDetailVo.setCategoryName(category.getName());
        //返回查询到的 vo
        return articleDetailVo;
    }

    /**
     * @Author lilin
     * @Date 2022/9/12 13:21:24
     * @param id 文章 id
     * @Return
     * @Description 修改文章浏览量
     */
    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
        System.out.println("文章浏览量加一");
        return ResponseResult.okResult();
    }

    /**
     * @Author lilin
     * @Date 2022/9/15 10:34:18
     * @param articleDto articleDto实体类
     * @Return
     * @Description 添加文章
     */
    @Override
    @Transactional      //标明此操作是 事务操作
    public ResponseResult add(AddArticleDto articleDto) {
        //添加博客
        Article article = CopyBeanUtils.copyBean(articleDto, Article.class);
        save(article);

        List<ArticleTag> articleTagList = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加博客和标签的关联
        articleTagService.saveBatch_(articleTagList);
        return ResponseResult.okResult();
    }
}
