package com.lilin.handler.exception;

import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by LiLin on 2022/9/7/20:27:22
 * 异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException exception) {
        //打印日志信息
        log.error("出现了异常 --> " + "\n" + exception);
        //从异常对象中获取提示信息，将其封装返回
        return ResponseResult.errorResult(exception.getCode(), exception.getMsg());
    }

    /**
     * @Author lilin
     * @Date 2022/9/7 20:35:51
     * @param exception 异常对象
     * @Return
     * @Description 处理项目中出现的其他没有被考虑到的异常
     */
    //@ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception exception) {
        //打印日志信息
        log.error("出现了异常 --> " + "\n" + exception);
        //从异常对象中获取提示信息，将其封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), exception.getMessage());
    }
}
