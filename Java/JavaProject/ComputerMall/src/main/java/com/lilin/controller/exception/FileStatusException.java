package com.lilin.controller.exception;

import com.lilin.controller.exception.father.FileUploadException;

/**
 * Created by LiLin on 2022/04/08/15:29
 *
 * 文件状态异常
 */
public class FileStatusException extends FileUploadException {
    public FileStatusException() {
    }

    public FileStatusException(String message) {
        super(message);
    }

    public FileStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStatusException(Throwable cause) {
        super(cause);
    }

    public FileStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
