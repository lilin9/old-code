package org.soft.base.enums;

import lombok.Getter;

/**
 * Created by LILIN on 2023/7/27/10:59:44
 * 响应枚举类，存放响应给前端的响应信息
 */
@Getter
public enum ResponseEnum {
    SUCCESS(200, "成功"),
    FAILED(500, "失败"),

    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_FAILED(500, "注册失败"),

    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAILED(500, "登录失败"),

    UPDATE_HUMAN_SUCCESS(200, "修改用户数据成功"),
    UPDATE_HUMAN_FAILED(500, "修改用户数据失败");


    private final Integer code;
    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
