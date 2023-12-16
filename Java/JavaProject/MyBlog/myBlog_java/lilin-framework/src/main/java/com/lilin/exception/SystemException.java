package com.lilin.exception;

import com.lilin.enums.AppHttpCodeEnum;

/**
 * Created by LiLin on 2022/9/7/20:18:38
 * 集中异常信息
 */
public class SystemException extends RuntimeException {
    /* 错误代码 */
    private Integer code;
    /* 错误信息 */
    private String msg;

    public SystemException(AppHttpCodeEnum appHttpCodeEnum) {
        super(appHttpCodeEnum.getMsg());
        this.code = appHttpCodeEnum.getCode();
        this.msg = appHttpCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
