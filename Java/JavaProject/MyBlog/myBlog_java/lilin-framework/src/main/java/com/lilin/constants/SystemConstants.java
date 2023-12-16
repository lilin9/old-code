package com.lilin.constants;

/**
 * Created by LiLin on 2022/9/4/17:28:41
 *
 * 常量类，存放项目会用到的字面量
 */
public class SystemConstants {
    /*
    文章类型：1 草稿；0 文章
     */
    public static final Integer ARTICLE_STATUS_DRAFT = 1;
    /*
    文章发布状态：0 发布；1 未发布
     */
    public static final Integer ARTICLES_STATUS_NORMAL = 0;
    /*
    正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /*
    赞助商链接审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /*
    登录时前台的 token 的 key 的前缀
     */
    public static final String TOKEN_PRE = "bloglogin";
    /*
    登录时后台的 token 的 key 的前缀
     */
    public static final String TOKEN_PRE_ADMIN = "login";
    /*
    文章评论id为 -1，表示此评论是根评论
     */
    public static final Integer COMMENT_IS_ROOT = -1;
    /*
    文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /*
    友链评论
     */
    public static final String LINK_COMMENT = "1";
    public static final String ARTICLE_VIEW_COUNT = "article:viewCount";
    /*
    表示是管理员用户
     */
    public static final Long IS_USER_ADMIN = 1L;
    /*
    permissions 中菜单类型为 菜单
     */
    public static final String MENU = "C";
    /*
    permissions 中菜单类型为 按钮
     */
    public static final String BUTTON = "F";
    /*
    正常状态
     */
    public static final String NORMAL = "0";
}