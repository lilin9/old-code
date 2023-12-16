package com.tianmao.controller.exception.codeException.father;

/**
 * Created by LiLin on 2022/6/2/17:41:39
 *
 * 验证码异常
 */
public class CodeException extends RuntimeException {
    public CodeException() {
        super();
    }

    public CodeException(String message) {
        super(message);
    }

    public CodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeException(Throwable cause) {
        super(cause);
    }

    protected CodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
