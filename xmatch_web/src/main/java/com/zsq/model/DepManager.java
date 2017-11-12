package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/5.
 * 部门管理员
 */
@Entity
public class DepManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long depManagerId;
    private String depManagerAccount;
    private int role;// 1 普通管理员 2 超级管理员
    private String password;
    private String phoneNum;
    private String email;
    /**管理员姓名*/
    private String managerName;
    /**管理员介绍*/
    private String managerSummary;
    /**管理的部门id 为 0 则没有管理部门*/
    private long departmentId;



    public String getManagerSummary() {
        return managerSummary;
    }

    public void setManagerSummary(String managerSummary) {
        this.managerSummary = managerSummary;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public int getRole() { return role; }

    public void setRole(int role) { this.role = role; }
}
