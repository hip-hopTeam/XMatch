package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DepManager {
    @Id
    private Long depManagerId;

    private String depManagerAccount;
    private String password;
    private String phoneNum;
    private String email;
    /**
     * 管理员姓名
     */
    private String managerName;
    /**
     * 管理员介绍
     */
    private String managerSummary;
    /**
     * 管理的部门id 为 0 则没有管理部门
     */
    private long departmentId;

    //角色 1 部门管理员 2 超级管理员
    private int role;



    @Generated(hash = 1303940366)
    public DepManager(Long depManagerId, String depManagerAccount, String password,
            String phoneNum, String email, String managerName,
            String managerSummary, long departmentId, int role) {
        this.depManagerId = depManagerId;
        this.depManagerAccount = depManagerAccount;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.managerName = managerName;
        this.managerSummary = managerSummary;
        this.departmentId = departmentId;
        this.role = role;
    }

    @Generated(hash = 1747122328)
    public DepManager() {
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getDepManagerId() {
        return depManagerId;
    }

    public void setDepManagerId(Long depManagerId) {
        this.depManagerId = depManagerId;
    }

    public String getDepManagerAccount() {
        return depManagerAccount;
    }

    public void setDepManagerAccount(String depManagerAccount) {
        this.depManagerAccount = depManagerAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}

