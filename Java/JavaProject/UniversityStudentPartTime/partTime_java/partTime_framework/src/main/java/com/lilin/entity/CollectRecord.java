package com.lilin.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (CollectRecord)表实体类
 *
 * @author makejava
 * @since 2022-09-25 15:16:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectRecord {

    @TableId
    private Long id;
    //用户 id
    private Long userId;
    //收藏的工作
    private Long jobId;
    //收藏的简历
    private Long resumeId;
    //收藏发起人 0 学生发起，1 企业发起，2 管理员
    private String collectUser;
    //是否被删除0未删除，1已删除
    private String isDelete;
    //创建人
    private String createBy;
    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人
    private String updateBy;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}

