package com.tianmao.controller.exception.codeException;

import com.tianmao.controller.exception.codeException.father.CodeException;

/**
 * Created by LiLin on 2022/6/2/17:43:01
 *
 * 验证码不匹配异常
 */
public class CodeMismatchException extends CodeException {
    public CodeMismatchException() {
        super();
    }

    public CodeMismatchException(String message) {
        super(message);
    }

    public CodeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeMismatchException(Throwable cause) {
        super(cause);
    }

    protected CodeMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
