package com.tianmao.service.exception;

import com.tianmao.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/05/17/12:45
 *
 * 收藏夹已存在异常
 */
public class CollectionDuplicateException extends ServiceException {
    public CollectionDuplicateException() {
        super();
    }

    public CollectionDuplicateException(String message) {
        super(message);
    }

    public CollectionDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionDuplicateException(Throwable cause) {
        super(cause);
    }

    public CollectionDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
