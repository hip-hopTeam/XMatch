package com.zsq.dto;

/**
 * Created by hp on 2017/11/10.
 */
public class DepManagerDto {
    private long depManagerId;

    private String depManagerAccount;
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

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
