package com.zsq.model;

import javax.persistence.*;

/**
 * Created by coderqiang on 2017/11/5.
 */
@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;

    @Column(unique = true)
    private String depName;//部门全称
    @Column(columnDefinition = "TEXT")
    private String depSummary;
    private int memberNum;//成员数量
    private int activityNum;//活动数量
    private int childDepNum;//子部门
    private long depManagerId;
    private long creatTime;//创建时间
    private String imageUrl;//部门logo

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
