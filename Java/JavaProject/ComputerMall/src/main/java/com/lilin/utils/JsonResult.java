package com.lilin.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/04/05/15:44
 *
 * 通过Json格式的数据进行响应
 */
@Data
@NoArgsConstructor
public class JsonResult<T> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //数据
    private T data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}
