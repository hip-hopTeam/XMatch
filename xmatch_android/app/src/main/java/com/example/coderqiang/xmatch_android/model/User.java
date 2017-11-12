package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by coderqiang on 2017/11/9.
 */

@Entity
public class User {
    @Id
    private Long userId;

    /**
     * 学号
     */
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

    private boolean isLogin;


    @Generated(hash = 925269380)
    public User(Long userId, String stuNo, String passwd, String username, int sex,
            String college, int bindPhone, String phoneNum, String email,
            boolean isLogin) {
        this.userId = userId;
        this.stuNo = stuNo;
        this.passwd = passwd;
        this.username = username;
        this.sex = sex;
        this.college = college;
        this.bindPhone = bindPhone;
        this.phoneNum = phoneNum;
        this.email = email;
        this.isLogin = isLogin;
    }

    @Generated(hash = 586692638)
    public User() {
    }
    

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
