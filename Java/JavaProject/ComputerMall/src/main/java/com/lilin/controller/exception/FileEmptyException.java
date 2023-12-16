package com.lilin.controller.exception;

import com.lilin.controller.exception.father.FileUploadException;

/**
 * Created by LiLin on 2022/04/08/15:20
 *
 * 文件为空异常
 */
public class FileEmptyException extends FileUploadException {
    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
