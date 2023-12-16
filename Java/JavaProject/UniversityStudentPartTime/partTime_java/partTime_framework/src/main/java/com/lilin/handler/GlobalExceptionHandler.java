package com.lilin.handler;

import com.lilin.constant.ResponseCodeEnum;
import com.lilin.exception.CustomizeException;
import com.lilin.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by LiLin on 2022/9/20/16:21:34
 * 全局统一异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @Author lilin
     * @Date 2022/9/20 16:39:59
     * @param customizeException 异常类
     * @Return
     * @Description 将可知异常集中处理发送给前端
     */
    @ExceptionHandler(value = CustomizeException.class)
    @ResponseBody
    public ResponseResult customizeExceptionHandler(CustomizeException customizeException) {
        //将异常信息打印到控制台
        log.error(customizeException.getMsg());
        //封装异常信息返回前端
        return ResponseResult.error(customizeException.getCode(), customizeException.getMsg());
    }

    /**
     * @Author lilin
     * @Date 2022/9/20 17:22:48
     * @param runtimeException 运行时异常
     * @Return
     * @Description 对不可知的、非自定义的异常进行捕获
     */
//    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseResult unknownException(RuntimeException runtimeException) {
        //将异常信息打印到控制台
        log.error(runtimeException.toString());
        //封装异常信息返回前端
        return ResponseResult.error(ResponseCodeEnum.FILED.getCode(), runtimeException.getMessage());
    }

}
