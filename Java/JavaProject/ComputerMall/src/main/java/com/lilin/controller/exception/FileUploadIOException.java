package com.lilin.controller.exception;

import com.lilin.controller.exception.father.FileUploadException;

/**
 * Created by LiLin on 2022/04/08/15:25
 *
 * 文件读写异常
 */
public class FileUploadIOException extends FileUploadException {
    public FileUploadIOException() {
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    public FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
