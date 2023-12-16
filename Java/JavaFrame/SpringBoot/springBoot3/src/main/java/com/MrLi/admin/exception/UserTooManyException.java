package com.MrLi.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by MrLi on 2022/03/25/10:27
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户数量太多")
public class UserTooManyException extends RuntimeException {
    public UserTooManyException() {}
    public UserTooManyException(String message) {
        super(message);
    }
}
