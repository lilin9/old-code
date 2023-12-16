package com.tianmao.service.exception;

import com.tianmao.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/05/17/22:21
 *
 * 收藏夹不存在
 */
public class CollectionNotFoundException extends ServiceException {
    public CollectionNotFoundException() {
        super();
    }

    public CollectionNotFoundException(String message) {
        super(message);
    }

    public CollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public CollectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
