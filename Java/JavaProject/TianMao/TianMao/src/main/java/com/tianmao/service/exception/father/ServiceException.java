package com.tianmao.service.exception.father;

/**
 * Created by LiLin on 2022/04/10/15:25
 *
 * 业务层异常基类
 */
public class ServiceException extends RuntimeException { //RuntimeException：运行时异常
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
