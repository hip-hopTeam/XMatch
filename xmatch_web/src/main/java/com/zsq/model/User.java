package com.zsq.model;

import javax.persistence.*;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 * 用户
 */
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 学号
     */
    @Column(unique = true,nullable = false)
    private String stuNo;
    /**
     * 教务处密码
     */
    private String passwd;
    /**
     * 姓名
     */
    private String username;
    /**
     * 性别
     */
    private int sex;
    /**
     * 学院
     */
    private String college;
    /**
     * 手机号  0 未绑定 1 绑定
     */
    private int bindPhone;

    /**手机号*/
    private String phoneNum;

    /**邮箱, 为 null则未绑定*/
    private String email;

    /**用户头像*/
    private String avatorUrl;

    /**个人简介**/
    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAvatorUrl() {
        return avatorUrl;
    }

    public void setAvatorUrl(String avatorUrl) {
        this.avatorUrl = avatorUrl;
    }

    public int getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(int bindPhone) {
        this.bindPhone = bindPhone;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
