package com.blogsweb.web.blogsweb.web.restcontroller;

import com.blogsweb.web.blogsweb.model.Results;
import com.blogsweb.web.blogsweb.model.User;
import com.blogsweb.web.blogsweb.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @类名: UserRestController
 * @包名: com.blogsweb.web.blogsweb.web.restcontroller
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/13 14:07
 * @版本: 1.0.0
 * <p>说明: </p>
 */
@RestController
@Api(tags = "用户功能接口")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId",value = "编号",required = true,dataType = "String",paramType = "path",example = "1002")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })

    @ApiOperation(value = "查找所用用户信息", notes = "查找所用用户信息")
    @GetMapping("findAllUser/{accountId}")
    public Results<Object> findAllUser(@PathVariable String accountId){
        Object ob = userService.findAllUser(accountId);
        Results<Object> results = new Results<Object>(200,"SUCCESS", ob);
        return results;
    }

    @PostMapping("/saveUser")
    @CrossOrigin
    @ApiOperation(httpMethod = "POST",value = "添加用户信息",notes = "添加用户信息")
    public Results<Object> saveUser(@RequestBody @ApiParam(name = "修改用户信息参数",value = "修改用户信息参数") User user, String accountId){
        Object ob = userService.saveUser(user,accountId);
        Results<Object> results = new Results<Object>(200,"SUCCESS", ob);
        return results;
    }
}
