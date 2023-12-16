package com.lilin.enums;

/**
 * Created by LiLin on 2022/9/4/14:07:21
 *
 * 枚举类，定义可能会向前端响应的信息
 */
public enum AppHttpCodeEnum {
    //成功相关
    SUCCESS(200, "操作成功"),
    //登录相关
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(402, "无权限操作"),
    SYSTEM_ERROR(403, "出现错误"),
    USERNAME_EXIST(404, "用户名已存在"),
    PHONE_EXIST(405, "手机号已存在"),
    REQUIRE_USERNAME(406, "必须填写用户名"),
    LOGIN_ERROR(407, "用户名或密码错误"),
    COMMENT_CONTENT_IS_NOT_NULL(408, "评论不能为空"),
    USERNAME_NOT_NULL(409, "用户名不能为空"),
    PASSWORD_NOT_NULL(410, "密码不能为空"),
    EMAIL_NOT_NULL(411, "邮箱不能为空"),
    NICKNAME_NOT_NULL(412, "昵称不能为空"),
    USER_EXIST(413, "用户已存在"),

    //文件上传相关
    FILE_TYPE_ERROR(501, "文件类型错误，请上传 png 或 jpg 文件");
    private final int code;
    private final String msg;

    AppHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
