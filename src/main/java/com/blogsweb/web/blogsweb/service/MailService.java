package com.blogsweb.web.blogsweb.service;

/**
 * @类名: MailService
 * @包名: com.blogsweb.web.blogsweb.service
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/13 16:20
 * @版本: 1.0.0
 * <p>说明: </p>
 */
public interface MailService {

    boolean sendSimpleMail(String to, String subject, String content);

    boolean sendHtmlMail(String to, String subject, String content);

    boolean sendAttachmentsMail(String to, String subject, String content, String filePath);

    boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
