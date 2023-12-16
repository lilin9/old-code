package com.tianmao.controller.exception.fileException;

import com.tianmao.controller.exception.fileException.father.FileUploadException;

/**
 * Created by LiLin on 2022/05/23/13:22
 *
 * 文件读写异常
 */
public class FileUploadIOException extends FileUploadException {
    public FileUploadIOException() {
        super();
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

    protected FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
