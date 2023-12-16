package com.tianmao.utils;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/04/10/16:20
 *
 * 处理请求响应的包装类
 */
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
