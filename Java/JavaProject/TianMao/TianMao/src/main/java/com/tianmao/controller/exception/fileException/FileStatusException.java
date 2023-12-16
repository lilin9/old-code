package com.tianmao.controller.exception.fileException;

import com.tianmao.controller.exception.fileException.father.FileUploadException;

/**
 * Created by LiLin on 2022/05/23/13:23
 *
 * 文件状态异常
 */
public class FileStatusException extends FileUploadException {
    public FileStatusException() {
        super();
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

    protected FileStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
