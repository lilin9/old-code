package com.lilin.service.exception;

import com.lilin.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/06/10:39
 *
 * 用户名对应的密码错误、密码匹配失败
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
