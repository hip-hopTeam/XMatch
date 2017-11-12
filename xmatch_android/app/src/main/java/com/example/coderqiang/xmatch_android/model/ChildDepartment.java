package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChildDepartment {
    @Id
    private Long childDepartmentId;

    private String name;
    private String phone;
    private String email;
    private long departmentId;

    @Generated(hash = 1003662808)
    public ChildDepartment(Long childDepartmentId, String name, String phone,
            String email, long departmentId) {
        this.childDepartmentId = childDepartmentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.departmentId = departmentId;
    }

    @Generated(hash = 1289939420)
    public ChildDepartment() {
    }


    public Long getChildDepartmentId() {
        return childDepartmentId;
    }

    public void setChildDepartmentId(Long childDepartmentId) {
        this.childDepartmentId = childDepartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}