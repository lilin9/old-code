package com.tianmao.service.exception;

import com.tianmao.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/12/17:02
 *
 * 用户密码不匹配异常
 */
public class PasswordNotMatchException extends ServiceException {
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
