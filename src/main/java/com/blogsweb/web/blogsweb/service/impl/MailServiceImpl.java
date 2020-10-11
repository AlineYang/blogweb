package com.blogsweb.web.blogsweb.service.impl;

/**
 * @类名: MailServiceImpl
 * @包名: com.blogsweb.web.blogsweb.service.impl
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/13 16:20
 * @版本: 1.0.0
 * <p>说明: </p>
 */
import com.blogsweb.web.blogsweb.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.sender}")
    private String from;
    @Override
    public boolean sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //helper.se
            helper.setFrom(from,"你的小可爱啊！");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("发送html邮件时发生异常！", e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public boolean sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from,"你的小可爱啊！");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            File file = new File(filePath);
            //获取文件名
            String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/")+1);
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from,"你的小可爱啊！");
            //helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            //FileSystemResource res = new FileSystemResource(new File(rscPath));
            //helper.addInline(rscId, res);
            //helper.add
            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
            return false;
        }
        return true;
    }
}