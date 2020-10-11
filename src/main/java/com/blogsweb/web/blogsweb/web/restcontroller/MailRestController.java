package com.blogsweb.web.blogsweb.web.restcontroller;

import com.blogsweb.web.blogsweb.model.Results;
import com.blogsweb.web.blogsweb.service.MailService;
import com.blogsweb.web.blogsweb.utils.StringUtils;
import com.sun.deploy.security.SelectableSecurityManager;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @类名: MailRestController
 * @包名: com.blogsweb.web.blogsweb.web.restcontroller
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/13 16:38
 * @版本: 1.0.0
 * <p>说明: </p>
 */
@RestController
@Api(tags = "邮箱发送接口")
public class MailRestController {

    @Autowired
    private MailService mailService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver",value = "接收的qq",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "subject",value = "主题",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "content",value = "内容",required = true,dataType = "String",paramType = "path",example = "1002")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "发送邮箱普通的信息", notes = "发送邮箱普通的信息")
    @GetMapping("sendSimpleMail/{receiver}/{subject}/{content}")
    public Results<Object> sendSimpleMail(@PathVariable String receiver, @PathVariable String subject,@PathVariable String content){
        Results<Object> results = null;
        if(StringUtils.isEmail(receiver)){
            mailService.sendSimpleMail(receiver,subject,content);
            results = new Results<Object>(200,"SUCCESS", "Send SUCCESS");
        }else{
            results = new Results<Object>(260,"Error", "Invalid mailbox format:非法的邮箱格式");
        }

        return results;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver",value = "接收的qq",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "subject",value = "主题",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "content",value = "内容",required = true,dataType = "String",paramType = "path",example = "1002")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "发送Html的邮件", notes = "发送Html的邮件")
    @GetMapping("sendHtmlMail/{receiver}/{subject}/{content}")
    public Results<Object> sendHtmlMail(@PathVariable String receiver, @PathVariable String subject,@PathVariable String content){
        Results<Object> results = null;
        if(StringUtils.isEmail(receiver)){
            content="<html>\n" +
                    "<body>\n" +
                    "    这是你的验证码：<strong><p>" +content+"</p></strong>\n 请妥善保管，该验证码在三分钟后失效" +
                    "</body>\n" +
                    "</html>";
            mailService.sendHtmlMail(receiver,subject,content);
            results = new Results<Object>(200,"SUCCESS", "Send SUCCESS");
        }else{
            results = new Results<Object>(260,"Error", "Invalid mailbox format:非法的邮箱格式");
        }
        return results;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver",value = "接收的qq",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "subject",value = "主题",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "content",value = "内容",required = true,dataType = "String",paramType = "path",example = "1002")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "发送邮箱普通的信息", notes = "发送邮箱普通的信息")
    @PostMapping("sendAttachmentsMail/{receiver}/{subject}/{content}")
    public Results<Object> sendAttachmentsMail(@PathVariable String receiver, @PathVariable String subject, @PathVariable String content, MultipartFile file){
        Results<Object> results = null;
        //StringUtils.isEmail(receiver)
        if(false){
            System.out.println(file.getResource().toString());
            mailService.sendAttachmentsMail(receiver, subject, content, file.getResource().toString());
            results = new Results<Object>(200,"SUCCESS", "Send SUCCESS");
        }else{
            results = new Results<Object>(260,"Error", "Invalid mailbox format:非法的邮箱格式");
        }
        return results;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver",value = "接收的qq",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "subject",value = "主题",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "content",value = "内容",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "imgPath",value = "Nginx的地址",required = true,dataType = "String",paramType = "path",example = "1002"),
            @ApiImplicitParam(name = "rscId",value = "内容",required = true,dataType = "String",paramType = "path",example = "1002")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "发送带图片的邮件", notes = "发送带图片的邮件")
    @GetMapping("sendInlineResourceMail/{receiver}/{subject}/{content}")
    public Results<Object> sendInlineResourceMail(@PathVariable String receiver, @PathVariable String subject, @PathVariable String content, String imgPath,String rscId){
        Results<Object> results = null;
        if(StringUtils.isEmail(receiver)){
            rscId = "";
            content="<html><body><br>这是有图片的邮件：</br><img src=\'" + imgPath + "\' width='80%' \n height='80%'\n></body></html>";
            boolean bool=mailService.sendInlineResourceMail(receiver, subject, content, imgPath, rscId);
            if(bool){
                results = new Results<Object>(200,"SUCCESS", "Send SUCCESS");
            }
            else{
                results = new Results<Object>(261,"Error", "发送失败");
            }
        }else{
            results = new Results<Object>(260,"Error", "Invalid mailbox format:非法的邮箱格式");
        }
        return results;

    }
}
