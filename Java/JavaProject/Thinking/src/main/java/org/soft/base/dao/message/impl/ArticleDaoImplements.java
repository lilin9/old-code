package org.soft.base.dao.message.impl;

import org.soft.base.dao.message.ArticleDao;
import org.soft.base.mapper.ArticleMapper;
import org.soft.base.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller("articleDao")
public class ArticleDaoImplements implements ArticleDao {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public boolean articleIssue(Article article) {
        boolean b = articleMapper.articleIssue(article);
        return b;
    }

    @Override
    public Article articleById(int articleId) {
        Article article = articleMapper.articleById(articleId);
        return article;
    }

    /**
     * @param idList idList
     * @Return
     * @Description 根据 id 列表查文章数据
     * @Author LILIN
     * @Date 2023/8/2 15:46:53
     */
    @Override
    public List<Article> articleByIdList(List<Integer> idList) {
        //空值确认
        if (idList.isEmpty()) return null;
        return articleMapper.articleByIdList(idList);
    }

    @Override
    public boolean articleUpdate(Article article) {
        boolean b = articleMapper.articleUpdate(article);
        return b;
    }

    @Override
    public List<Article> articlesByMap(Map<String, Object> map) {
        List<Article> articles = articleMapper.articlesByMap(map);
        return articles;
    }

    @Override
    public boolean articleDeleteById(int articleId) {
        boolean b = articleMapper.articleDeleteById(articleId);
        return b;
    }

    @Override
    public List<Article> articlesByHumanId(Map<String, Object> map) {
        List<Article> articles = articleMapper.articlesByHumanId(map);
        return articles;
    }

    @Override
    public List<Article> articlesByTypeMap(Map<String, Object> map) {
        List<Article> articles = articleMapper.articlesByTypeMap(map);
        return articles;
    }
}
