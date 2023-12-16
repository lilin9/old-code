package com.lilin.service.exception;

import com.lilin.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/06/10:41
 *
 * 用户名没有找到异常
 */
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
