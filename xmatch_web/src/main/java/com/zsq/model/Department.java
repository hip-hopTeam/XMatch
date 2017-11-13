package com.zsq.model;

import javax.persistence.*;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/5.
 * 部门
 */
@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;

    /**部门全称*/
    @Column(unique = true)
    private String depName;

    /**部门简介*/
    @Column(columnDefinition = "TEXT")
    private String depSummary;
    /**成员数量*/
    private int memberNum;
    /**部门状态 1 申请中 2 审核通过 3 审核失败*/
    private int state;
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

    /**
     * 申请数量
     */
    private int applyNum;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getChildDepNum() {
        return childDepNum;
    }

    public void setChildDepNum(int childDepNum) {
        this.childDepNum = childDepNum;
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
}
