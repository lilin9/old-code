package com.lilin.service.exception;

import com.lilin.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/05/13:55
 *
 * 用户名被占用异常
 */
public class UsernameDuplicateException extends ServiceException {
    public UsernameDuplicateException() {
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
