package com.lilin.controller.exception;

import com.lilin.controller.exception.father.FileUploadException;

/**
 * Created by LiLin on 2022/04/08/15:23
 *
 * 文件类型异常
 */
public class FileTypeException extends FileUploadException {
    public FileTypeException() {
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
