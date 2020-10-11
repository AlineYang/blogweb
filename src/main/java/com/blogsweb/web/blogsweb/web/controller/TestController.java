package com.blogsweb.web.blogsweb.web.controller;

import com.blogsweb.web.blogsweb.model.Results;
import com.blogsweb.web.blogsweb.model.User;
import com.blogsweb.web.blogsweb.service.UserService;
import com.blogsweb.web.blogsweb.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @类名: TestController
 * @包名: com.blogsweb.web.blogsweb.controller
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/12 17:31
 * @版本: 1.0.0
 * <p>说明: </p>
 */
@Controller
@Api(tags = "测试功能接口")
public class TestController {



    @GetMapping("/")
    @CrossOrigin
    @ApiOperation(value = "主界面的接口", notes = "项目主界面接口")
    public String getHello() {
        return "forward: doc.html";
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名称",required = true,dataType = "String",paramType = "path",example = "blues")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "测试接口", notes = "测试接口")
    @ResponseBody
    @GetMapping("hello/{name}")
    public Results<UserVO> hello(@PathVariable String name){
        //System.out.printf("name==========="+name);
        UserVO userVO = new UserVO(name,"hello " + name);
        Results<UserVO> results = new Results<>(200,"SUCCESS", userVO);
        return results;
    }



}
