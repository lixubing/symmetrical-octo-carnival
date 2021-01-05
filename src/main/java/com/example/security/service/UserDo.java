package com.example.security.service;

import com.example.security.BaseDo;

import java.util.List;

public class UserDo extends BaseDo {
    private String loginName;
    private String username;
    private String password;
    private List<RoleDo> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<RoleDo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDo> roles) {
        this.roles = roles;
    }

    public String getLoginname() {
        return loginName;
    }

    public void setLoginname(String loginname) {
        this.loginName = loginname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UserDo getUserByLoginName(){
        return (UserDo) getObject("UserDo.getUserByLoginName", this);
    }
}
