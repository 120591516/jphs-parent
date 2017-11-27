package com.jinpaihushi.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SimpleEmail {
    /**
     * 发送邮件 (阿里云邮箱企业版)
     * 
     * @param fromEmail
     *            发送邮箱
     * @param toEmail
     *            接收邮箱
     * @param emailName
     *            阿里云邮箱登录名
     * @param emailPassword
     *            密码
     * @param title
     *            发送主题
     * @param centent
     *            发送内容
     * @throws Exception
     */
    public static void sendMail(String fromEmail, String toEmail, String emailName, String emailPassword, String title,
            String centent) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.goldnurse.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect(emailName, emailPassword);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(title);
        message.setContent(centent, "text/html;charset=utf-8");
        ts.sendMessage(message, message.getAllRecipients());
    }

    public static void main(String[] args) throws Exception {
        SimpleEmail.sendMail("service@goldnurse.com", "java@goldnurse.com", "service@goldnurse.com", "goldNurse#123",
                "Demo", "测试邮件");
    }

    private MailSender mailSender;

    private SimpleMailMessage simpleMailMessage;

    /**
     * @方法名: sendMail 
     * @参数名：@param subject  邮件主题
     * @参数名：@param content 邮件主题内容
     * @参数名：@param to         收件人Email地址
     * @描述语: 发送邮件
     */
    public void sendMail(String subject, String content, String toName) {

        simpleMailMessage.setSubject(subject); //设置邮件主题
        simpleMailMessage.setTo(toName); //设定收件人
        simpleMailMessage.setText(content); //设置邮件主题内容
        mailSender.send(simpleMailMessage); //发送邮件
    }

    //Spring 依赖注入
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    //Spring 依赖注入 
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
