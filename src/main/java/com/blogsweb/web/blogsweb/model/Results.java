package com.blogsweb.web.blogsweb.model;

import com.blogsweb.web.blogsweb.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("通用接口返回对象")
public class Results<T> {

    @ApiModelProperty(required = true,notes = "结果码",example = "200")
    private int state;
    @ApiModelProperty(required = true,notes = "时间戳",example = "1567425139000")
    private String time;
    @ApiModelProperty(required = true,notes = "返回信息",example = "SUCCESS")
    private String message;
    @ApiModelProperty(required = true,notes = "返回数据",example = "data")
    private T content;

    public Results(int code, String msg, T obj){
        setState(code);
        setMessage(msg);
        setContent(obj);
        setTime(DateUtils.formatLong(new Date()));
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
