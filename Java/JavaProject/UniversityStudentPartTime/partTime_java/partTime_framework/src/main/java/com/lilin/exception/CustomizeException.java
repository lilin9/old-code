package com.lilin.exception;

import com.lilin.constant.ResponseCodeEnum;
import lombok.AllArgsConstructor;

/**
 * Created by LiLin on 2022/9/20/16:31:09
 * 自定义异常
 */
@AllArgsConstructor
public class CustomizeException extends RuntimeException {
    /*
    异常代码
     */
    private final Integer code;
    /*
    异常信息
     */
    private final String msg;

    public CustomizeException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
