package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by coderqiang on 2017/11/5.
 */
@Entity
public class ChildDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long childDepartmentId;

    private String name;
    private String phone;
    private String email;
    private long departmentId;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getChildDepartmentId() {
        return childDepartmentId;
    }

    public void setChildDepartmentId(long childDepartmentId) {
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
}

