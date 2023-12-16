package com.tianmao.service.impl;

import com.tianmao.pojo.Mail;
import com.tianmao.service.MyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by LiLin on 2022/6/2/14:57:49
 *
 * 发送邮箱的业务类的实现类
 */
@Service
public class MyEmailImpl implements MyEmail {
    @Autowired
    JavaMailSender mailSender;

    /**
     * @Author lilin
     * @Date 2022/6/2 14:55:56
     * @param mail 邮件接收者会接收到的信息
     * @Description 发送邮件
     */
    @Override
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        //封装邮件信息
        try {
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}


















