package com.lilin.service.exception;

import com.lilin.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/04/12/10:27
 *
 * 查询到的结果总数大于20，抛出业务控制的异常
 */
public class AddressCountLimitException extends ServiceException {
    public AddressCountLimitException() {
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
