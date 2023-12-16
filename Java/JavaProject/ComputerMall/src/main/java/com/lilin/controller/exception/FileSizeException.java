package com.lilin.controller.exception;

import com.lilin.controller.exception.father.FileUploadException;

/**
 * Created by LiLin on 2022/04/08/15:22
 *
 * 文件大小超出限制异常
 */
public class FileSizeException extends FileUploadException {
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
