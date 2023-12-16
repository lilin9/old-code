package com.lilin.service.exception;

import com.lilin.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/16/10:56
 *
 * 要查询的购物车数据不存在异常
 */
public class CartNotFoundException extends ServiceException {
    public CartNotFoundException() {
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }

    public CartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
