package com.tianmao.controller.exception.fileException;

import com.tianmao.controller.exception.fileException.father.FileUploadException;

/**
 * Created by LiLin on 2022/05/23/13:20
 *
 * 文件为空异常
 */
public class FileEmptyException extends FileUploadException {
    public FileEmptyException() {
        super();
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

    protected FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
