package com.lilin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/9/15/10:37:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "article_tag")
public class ArticleTag implements Serializable {
    public static final Long serialVersionUID = 625337492348897098L;
    /*
    文章 id
     */
    private Long articleId;
    /*
    标签 id
     */
    private Long tagId;
}
