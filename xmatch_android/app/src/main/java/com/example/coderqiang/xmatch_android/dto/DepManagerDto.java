package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepManagerDto {
    private long depManagerId;

    private String depManagerAccount;
    private String phoneNum;
    private String email;
    private int applyNum;
    /**管理员姓名*/
    private String managerName;
    /**管理员介绍*/
    private String managerSummary;
    /**角色 1 部门管理员 2 超级管理员*/
    private int role;
    /** 头像*/
    private String avatorUrl;


    //部门相关
    private long departmentId;
    /**部门全称*/
    private String depName;
    /**部门简介*/
    private String depSummary;
    /**成员数量*/
    private int memberNum;
    /**部门状态 1 申请中 2 审核通过 3 审核失败*/
    private int state;
    /**活动数量*/
    private int activityNum;
    /**子部门*/
    private int childDepNum;
    /**创建时间*/
    private long creatTime;
    /**部门logo*/
    private String imageUrl;
    /**紧急联系方式*/
    private String emergencyPhone;

    public String getAvatorUrl() {
        return avatorUrl;
    }

    public void setAvatorUrl(String avatorUrl) {
        this.avatorUrl = avatorUrl;
    }

    public long getDepManagerId() {
        return depManagerId;
    }

    public void setDepManagerId(long depManagerId) {
        this.depManagerId = depManagerId;
    }

    public String getDepManagerAccount() {
        return depManagerAccount;
    }

    public void setDepManagerAccount(String depManagerAccount) {
        this.depManagerAccount = depManagerAccount;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerSummary() {
        return managerSummary;
    }

    public void setManagerSummary(String managerSummary) {
        this.managerSummary = managerSummary;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepSummary() {
        return depSummary;
    }

    public void setDepSummary(String depSummary) {
        this.depSummary = depSummary;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(int activityNum) {
        this.activityNum = activityNum;
    }

    public int getChildDepNum() {
        return childDepNum;
    }

    public void setChildDepNum(int childDepNum) {
        this.childDepNum = childDepNum;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
}
