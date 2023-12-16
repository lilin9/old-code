package com.lilin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by LiLin on 2022/9/20/14:16:51
 * 响应码，定义返回的错误码
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnum {
    SUCCESS(200, "成功"),
    FILED(500, "失败"),

    PARAMS_IS_NULL(504, "参数为空"),
    SEARCH_CONTENT_IS_EMPTY(503, "搜索内容不能为空"),
    PASSWORD_NOT_FOUND(502, "密码不能为空"),
    USERNAME_NOT_FOUND(501, "用户名不能为空"),

    /* 查 400*/
    CITY_IS_NULL(419, "城市不存在"),
    CITY_NAME_IS_NOT_EMPTY(418, "城市名重复"),
    CITY_ID_IS_NOT_EMPTY(417, "城市ID重复"),
    USER_CARE_IS_NOT_EXIST(416, "用户关注不存在"),
    COLLECT_IS_NOT_EXIST(415, "用户收藏不存在"),
    USER_IS_DELETE(414, "用户已经被删除"),
    MENU_IS_EMPTY(413, "查不到菜单数据"),
    USER_NOT_IS_ADMIN(412, "不是管理员用户，不能登录后台系统"),
    COLLECT_FAIL(411, "用户不能收藏自己的职位"),
    SEARCH_POSITIONS_FAIL(410, "查询不到职位信息"),
    COMP_JOB_INFO_LIST_EMPTY(409, "用户没有发布过职位"),
    CARE_LIST_IS_EMPTY(408, "关注列表为空"),
    COLLECT_IS_EXIST(407, "收藏夹已存在"),
    PLEASE_TO_LOGIN(406, "请先登录"),
    COLLECT_LIST_IS_EMPTY(405, "用户收藏夹为空"),
    POSITIONS_NOT_EXIST(404, "此职位信息不存在"),
    PASSWORD_FAIL(403, "密码错误"),
    USER_NOT_EXIST(402, "用户不存在"),
    USERNAME_PASSWORD_FAIL(401, "用户名或密码错误"),
    USER_IS_EXIST(400, "用户名已存在"),

    /* 增 300 */
    ADD_CITY_FAIL(304, "添加城市信息失败"),
    ADD_COMP_JOB_INFO_FAIL(303, "发布职位信息失败"),
    COMP_JOB_INFO_IS_NULL(302, "职位信息为空"),
    ADD_CARE_FAIL(301, "添加关注失败"),
    ADD_COLLECT_FAIL(300, "添加收藏失败"),

    /* 删 600 */
    LOGOUT_FAIL(605, "退出登录失败"),
    DELETE_POSITIONS_FAIL(604, "删除职位信息失败"),
    DELETE_CITY_FAIL(603, "删除城市失败"),
    DELETE_COLLECT_FAIL(602, "删除用户收藏失败"),
    DELETE_USER_FAIL(601, "删除用户失败"),
    SUPER_ADMIN_IS_NOT_DELETE(600, "超级管理员不可以被删除"),


    /* 改 700 */
    UPDATE_POSITIONS_FAIL(704, "修改职位信息失败"),
    UPDATE_USER_FAIL(703, "修改用户数据失败"),
    UPDATE_STATUS_FAIL(702, "修改用户状态失败"),
    UPDATE_CARE_FAIL(701, "修改关注失败"),
    UPDATE_COLLECT_FAIL(700, "修改用户收藏失败");

    /*
    异常代码
     */
    public final Integer code;

    /*
    异常信息
     */
    public final String msg;
}
