package com.lilin.utils;

import com.lilin.constant.ResponseCodeEnum;
import lombok.Data;

/**
 * Created by LiLin on 2022/9/20/14:08:40
 * 统一返回结果实体类
 */
@Data
public class ResponseResult<T> {
    /*
    状态码
     */
    private Integer code;
    /*
    响应信息
     */
    private String msg;
    /*
    响应的数据
     */
    private T data;

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(data);
    }

    public static ResponseResult error(Integer code, String msg) {
        ResponseResult<Object> responseResult = new ResponseResult<>();
        return responseResult.result(code, msg);
    }

    public static ResponseResult error(ResponseCodeEnum statusEnums) {
        ResponseResult<Object> responseResult = new ResponseResult<>();
        return responseResult.result(statusEnums.getCode(), statusEnums.getMsg());
    }

    public ResponseResult() {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(ResponseCodeEnum statusEnums) {
        this.code = statusEnums.getCode();
        this.msg = statusEnums.getMsg();
    }

    /**
     * @Author lilin
     * @Date 2022/9/20 14:34:30
     * @param code 状态码
     * @param msg 返回的信息
     * @Return
     * @Description 如果没有数据，可以人为返回 状态码 和 状态信息
     */
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Author lilin
     * @Date 2022/9/20 14:35:44
     * @param data 返回的数据
     * @Return
     * @Description 有数据返回时，状态码默认 200，状态信息默认 成功
     */
    public ResponseResult(T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * @Author lilin
     * @Date 2022/9/20 14:37:27
     * @param msg 状态信息
     * @param data 返回的数据
     * @Return
     * @Description 状态码默认 200，状态信息和返回数据人为指定
     */
    public ResponseResult(String msg, T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult<?> result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        assert data != null;
        this.data = data;
        return this;
    }
}
