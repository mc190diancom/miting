package com.feidi.template.mvp.model.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String id;

    private String account;

    private String password;

    private String username;

    private int status;

    private int errorCount;

    private long updateTime;

    private long createTime;

    private long lastLoginTime;

    private String roleIds;

    private String roleList;

    private String resourceList;

    private String phoneNo;

    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleIds() {
        return this.roleIds;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    public String getRoleList() {
        return this.roleList;
    }

    public void setResourceList(String resourceList) {
        this.resourceList = resourceList;
    }

    public String getResourceList() {
        return this.resourceList;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
