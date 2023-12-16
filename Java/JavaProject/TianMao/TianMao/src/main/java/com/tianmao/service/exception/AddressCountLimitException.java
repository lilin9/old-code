package com.tianmao.service.exception;

import com.tianmao.service.exception.father.ServiceException;

/**
 * Created by LiLin on 2022/05/23/21:38
 *
 * 收货地址数量超出限制异常
 */
public class AddressCountLimitException extends ServiceException {
    public AddressCountLimitException() {
        super();
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
