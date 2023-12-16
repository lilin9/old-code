package org.soft.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.base.enums.ResponseEnum;

import java.io.Serializable;

/**
 * Created by LILIN on 2023/7/27/10:54:56
 * 响应返回类
 */
@NoArgsConstructor
@Data
public class ResponseResult<T> implements Serializable {
    //返回代码  统一设置：200成功，500 错误
    private Integer code;
    //返回信息
    private String message;
    //返回数据
    private T data;

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public ResponseResult(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
}
