package com.tianmao.controller;

import com.tianmao.pojo.Mail;
import com.tianmao.service.MyEmail;
import com.tianmao.utils.JsonResult;
import com.tianmao.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by LiLin on 2022/6/2/15:25:33
 *
 * 邮件控制层
 */
@RestController
@RequestMapping("mails")
public class MailController extends BaseController {
    @Autowired
    MyEmail myEmail;

    @GetMapping("getCode")
    public JsonResult<Void> getCode(String email, HttpSession session) {
        //获取验证码
        String code = RandomNumber.random();
        //将验证码保存到session域中
        session.setAttribute("code", code);
        session.setMaxInactiveInterval(900);    //设置session15分钟后失效

        Mail mail = new Mail();
        mail.setFrom("1541358724@qq.com");
        mail.setTo(email);
        mail.setSubject("注册账号，邮箱验证");
        //html格式的正文
        String text = "<div style=\"padding: 20px;line-height: 1.7\">\n" +
                "    <p>您好 <a href=\"" + email + "\" rel=\"noopener\" target=\"_blank\" style=\"color: #FE7300\">" + email + "</a>！</p>\n" +
                "    <p>欢迎注册 PCJP商城，请将验证码填写到注册页面。</p>\n" +
                "    <p>验证码：" + code + "</p>\n" +
                "    <p>识别码：RFC</p>\n" +
                "</div>";
        mail.setText(text);

        //发送邮件
        myEmail.sendEmail(mail);

        return new JsonResult<>(OK);
    }
}
