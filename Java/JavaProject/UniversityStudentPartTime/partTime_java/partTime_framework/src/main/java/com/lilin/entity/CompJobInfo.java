package com.lilin.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (CompJobInfo)表实体类
 *
 * @author makejava
 * @since 2022-09-24 20:49:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "comp_job_info")
public class CompJobInfo {

    @TableId
    private Long id;

    //兼职类别 类别名称
    private String jobCate;
    //工作地点
    private String jobPlace;
    //详细工作地点
    private String jobDetailPlace;
    //工作待遇
    private Double jobTreat;
    //待遇方式 小时/天/月
    private String treatMethod;
    //结算方式 当日结，次日结，月结，完工结
    private String payMethod;
    //兼职标题
    private String jobTitle;
    //工作内容
    private String jobContent;
    //是否启用: 0启用 1不启用 2删除
    private Integer isAvailable;
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
    //联系人ID
    private Long contactId;

}

