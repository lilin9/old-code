package com.lilin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/9/25/14:49:39
 * 职位信息详情 vo 类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionsDetailVo {
    //职位 id
    private Long id;
    //兼职类别 类别名称
    private String jobCate;
    //兼职标题
    private String jobTitle;
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
    //工作内容
    private String jobContent;
    //是否启用: 0启用 1不启用 2删除
    private Integer isAvailable;
    //更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //联系人
    private PublisherVo publisher;
}
