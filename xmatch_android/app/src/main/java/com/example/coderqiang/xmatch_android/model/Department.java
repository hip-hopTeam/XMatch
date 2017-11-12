package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Department {

    @Id
    private Long departmentId;

    /**部门全称*/
    private String depName;
    /**部门简介*/
    private String depSummary;
    /**成员数量*/
    private int memberNum;
    /**活动数量*/
    private int activityNum;
    /**子部门*/
    private int childDepNum;
    /**部门管理人员Id*/
    private long depManagerId;
    /**创建时间*/
    private long creatTime;
    /**部门logo*/
    private String imageUrl;
    /**紧急联系方式*/
    private String emergencyPhone;


    @Generated(hash = 14612787)
    public Department(Long departmentId, String depName, String depSummary,
            int memberNum, int activityNum, int childDepNum, long depManagerId,
            long creatTime, String imageUrl, String emergencyPhone) {
        this.departmentId = departmentId;
        this.depName = depName;
        this.depSummary = depSummary;
        this.memberNum = memberNum;
        this.activityNum = activityNum;
        this.childDepNum = childDepNum;
        this.depManagerId = depManagerId;
        this.creatTime = creatTime;
        this.imageUrl = imageUrl;
        this.emergencyPhone = emergencyPhone;
    }

    @Generated(hash = 355406289)
    public Department() {
    }


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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

    public long getDepManagerId() {
        return depManagerId;
    }

    public void setDepManagerId(long depManagerId) {
        this.depManagerId = depManagerId;
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
