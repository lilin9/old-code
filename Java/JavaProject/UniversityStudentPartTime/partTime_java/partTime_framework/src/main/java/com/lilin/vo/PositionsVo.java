package com.lilin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/24/15:48:54
 * 职位信息 vo 类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionsVo {
    //职位 id
    private Long id;
    //兼职标题
    private String jobTitle;
    //工作待遇
    private Double jobTreat;
    //待遇方式 小时/天/月
    private String treatMethod;
    //结算方式 当日结，次日结，月结，完工结
    private String payMethod;
    //工作内容
    private String jobContent;
    //联系人
    private PublisherVo publisher;
    //工作地点
    private String jobPlace;
}






