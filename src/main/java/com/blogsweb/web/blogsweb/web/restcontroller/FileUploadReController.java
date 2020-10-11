package com.blogsweb.web.blogsweb.web.restcontroller;

import com.blogsweb.web.blogsweb.model.Results;
import com.blogsweb.web.blogsweb.utils.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 作者：杨冕
 * 包名：com.haiwei.parkweb.restcontroller.system
 * 类名：FileUploadReController
 * 创建时间：2019/5/11
 * 描述信息：
 */
@RestController
@CrossOrigin
@RequestMapping("/FileUpload/*")
@Api(value = "文件上传的接口",tags = {"文件上传的接口"})
public class FileUploadReController {

    @Value("${ftp.ftp-host}")
    private String host;

    @Value("${ftp.ftp-port}")
    private int port;

    @Value("${ftp.ftp-username}")
    private String username;


    @Value("${ftp.ftp-password}")
    private String password;


    @Value("${ftp.ftp-basePath}")
    private String basePath;

    @Value("${nginx.nginx-url}")
    private String nginxUrl;


    public static final String PATH ="/src/main/resources/";

    @PostMapping(value="/fileUpload",headers="content-type=multipart/form-data")
    @ApiOperation(value = "上传图片的接口",notes = "上传图片的接口",httpMethod = "POST")
    @CrossOrigin
    public String fileUpload( @ApiParam(value="项目的SVG",required=true) MultipartFile file) {
        //获取文件的老名称
        String oldFileName = file.getOriginalFilename();

        //获取文件要保存的路径
        String url1 = String.valueOf(ClassLoader.getSystemResource(""));
        String url = url1.replace("/target/classes/","");
        String newUrl =url.replace("file:/","");


        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = uuid+oldFileName.substring(oldFileName.lastIndexOf("."));

        //设置文件存放的位置
        String strNow = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
        String roolPath = newUrl+PATH+"static/systemimg/"+strNow+"/"+newFileName;

        //
        File files = new File(roolPath);

        if(!files.getParentFile().exists()){
            files.getParentFile().mkdirs();
        }else{

        }
        try {
            if(!files.exists()){
                //System.out.println("存入文件");
                file.transferTo(files);
            }
        }catch (Exception e){

        }
        return "systemimg/"+strNow+"/"+newFileName;
    }


    @PostMapping(value="/ftpfileUpload",headers="content-type=multipart/form-data")
    @ApiOperation(value = "ftp上传图片的接口",notes = "ftp上传图片的接口",httpMethod = "POST")
    @CrossOrigin
    public Results<Object> ftpfileUpload(@ApiParam(value="Ftp文件上传入口",required=true) MultipartFile file) {

        //C:/Users/Administrator/Desktop/img/image
        //获取文件的老名称
        System.out.println("ftp文件上传");
        String oldFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = uuid+oldFileName.substring(oldFileName.lastIndexOf("."));

        //设置文件存放的位置
        String strNow = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();

        String fielPath = strNow+"/"+newFileName;
         String url=nginxUrl+fielPath;
        InputStream iput = null;
        try {
            iput = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean flag = FtpUtil.uploadFile(host,port,username,password,basePath,strNow,newFileName,iput);
        if(flag){
            Results<Object> results = new Results<Object>(200,"SUCCESS", url);
            return results;
        }
        String filed = "FtpfileUpload filed";
        Results<Object> results = new Results<Object>(235,"Error", filed);
        return results;
    }


}
