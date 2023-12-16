package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/9/6/15:13:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类 id
    private Long categoryId;
    //所属分类名
    private String categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    //创建时间
    private Date createTime;
    //文章内容
    private String content;
}
