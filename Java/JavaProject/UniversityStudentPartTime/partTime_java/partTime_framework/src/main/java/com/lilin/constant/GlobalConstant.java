package com.lilin.constant;

/**
 * Created by LiLin on 2022/9/22/16:24:11
 * 定义全局会用到的字面量
 */
public class GlobalConstant {
    public static final String ADMIN = "admin";

    /*
    普通用户的 redis key
     */
    public static final String LOGIN_USER_KEY = "userInfo_";
    /*
    后台管理员用户的 redis key
     */
    public static final String LOGIN_ADMIN_USER_KEY = "adminUserInfo_";


    /*
    jwt 有效时间
     */
    public static final Long JWT_TTL = 86400000L;  // 24 个小时
    /*
    jwt 秘钥明文
     */
    public static final String JWT_KEY = "part_time";


    /*
    管理员用户
     */
    public static final String USER_TYPE_ADMIN = "2";
    /*
    企业用户
     */
    public static final String USER_TYPE_COMPANY = "1";
    /*
    学生用户
     */
    public static final String USER_TYPE_NORMAL = "0";

    /*
    未知用户
     */
    public static final String USER_SEX_UNKNOWN = "2";

    /*
    是否被删除： 1 已经被删除
     */
    public static final String IS_DELETE = "1";
    /*
    是否被删除： 0 没有被删除
     */
    public static final String NO_DELETE = "0";

    /*
    是否启用: 0 启用
     */
    public static final Integer IS_AVAILABLE = 0;
    /*
    是否启用: 1 不启用
     */
    public static final Integer NO_AVAILABLE = 1;
    /*
    是否启用: 2 删除
     */
    public static final Integer DELETE_AVAILABLE = 2;


    /*
    job_place
     */
    public static final String CITY_NAME = "job_place";
    /*
    job_title
     */
    public static final String POSITIONS_NAME_COLUMN = "job_title";
    /*
    user_name
     */
    public static final String USERNAME = "user_name";
    /*
    nick_name
     */
    public static final String NICK_NAME = "nick_name";
    /*
    create_time
     */
    public static final String CREATE_TIME = "create_time";
    /*
    is_delete
     */
    public static final String IS_DELETE_ = "is_delete";
    /*
    user_id
     */
    public static final String USER_ID = "user_id";
    /*
    care_id
     */
    public static final String CARE_ID = "care_id";
    /*
    update_by
     */
    public static final String UPDATE_BY = "update_by";
    /*
    job_title
     */
    public static final String JOB_TITLE = "job_title";
    /*
    job_cate
     */
    public static final String JOB_CATE = "job_cate";
    /*
    job_detail_place
     */
    public static final String JOB_DETAIL_PLACE = "job_detail_place";


    /*
    无需认证即可访问，游客身份。
     */
    public static final String ANON = "anon";
    /*
    必须认证（登录）才能访问。
     */
    public static final String AUTHC = "authc";

    /*
    是后台请求
     */
    public static final String IS_ADMIN = "1";
    /*
    不是后台请求
     */
    public static final String NO_ADMIN = "0";

    /*
    菜单表的顶层 parentId，默认是 0
     */
    public static final Long DEFAULT_PARENT_ID = 0L;
}
