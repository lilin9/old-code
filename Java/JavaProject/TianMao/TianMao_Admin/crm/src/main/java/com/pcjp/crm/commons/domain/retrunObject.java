package com.pcjp.crm.commons.domain;

/**
 * @ClassName retrunObject
 * @Description 返回信息实体类
 * @Author Jiang
 * @Date 2022/5/4 20:22
 * @Version 1.0
 **/
public class retrunObject {
    private String code; //成功或失败的标记 1-成功 0-失败
    private String message; //提示信息
    private Object reDate; //返回其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReDate() {
        return reDate;
    }

    public void setReDate(Object reDate) {
        this.reDate = reDate;
    }
}
