package com.blogsweb.web.blogsweb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("User对象")
public class User implements Serializable {

    @ApiModelProperty(required = true,notes = "编号",hidden = true)
    private int	userId;

    @ApiModelProperty(required = true,notes = "编码",example ="AD1002")
    private String 	userCode;

    @ApiModelProperty(required = true,notes = "性别",example ="男")
    private char sex;

    @ApiModelProperty(required = true,notes = "昵称",example ="阿斯加德")
    private String 	nickname;

    @ApiModelProperty(required = true,notes = "真实姓名",example ="马化腾")
    private String 	realname;

    @ApiModelProperty(required = true,notes = "用户名",example ="user")
    private String 	username;

    @ApiModelProperty(required = true,notes = "电话",example ="1554648884")
    private String 	tel;



    @ApiModelProperty(required = true,notes = "密码",example ="155564")
    private String 	passwords;

    @ApiModelProperty(notes = "邮箱",example ="1477699332@qq.com")
    private String 	email;

    public User() {
        super();
    }
    public User(int userId, String userCode, char sex, String nickname, String realname, String username, String tel, String passwords, String email) {
        this.userId = userId;
        this.userCode = userCode;
        this.sex = sex;
        this.nickname = nickname;
        this.realname = realname;
        this.username = username;
        this.tel = tel;
        this.passwords = passwords;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
