package com.lilin.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LiLin on 2022/04/05/9:07
 *
 * 作为实体类的基类
 */
@Data
public class BaseEntity implements Serializable {
    private String createdUser;     //日志-创建人
    private Date createdTime;       //日志-创建时间
    private String modifiedUser;    //日志-最后修改人
    private Date modifiedTime;      //日志-最后执行时间
}
