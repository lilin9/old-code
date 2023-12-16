package com.tianmao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/6/2/15:08:51
 *
 * 封装发送邮件时，接收者接收到的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {
    private String from;        //发件人
    private String to;          //收件人
    private String subject;     //邮件主题
    private String text;        //邮件内容
}
