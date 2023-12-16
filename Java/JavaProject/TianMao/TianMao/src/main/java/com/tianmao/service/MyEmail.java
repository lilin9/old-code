package com.tianmao.service;

import com.tianmao.pojo.Mail;

/**
 * Created by LiLin on 2022/6/2/14:49:20
 *
 * 发送邮箱的业务类
 */
public interface MyEmail {
    /**
     * @Author lilin
     * @Date 2022/6/2 14:55:56
     * @param mail 邮件接收者会接收到的信息
     * @Description 发送邮件
     */
    void sendEmail(Mail mail);
}
