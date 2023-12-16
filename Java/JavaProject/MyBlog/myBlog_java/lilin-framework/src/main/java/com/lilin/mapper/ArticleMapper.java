package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiLin on 2022/9/3/16:01:19
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
