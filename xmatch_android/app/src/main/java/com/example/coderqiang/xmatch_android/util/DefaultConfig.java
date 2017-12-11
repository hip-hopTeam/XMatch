package com.example.coderqiang.xmatch_android.util;

import android.content.Context;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class DefaultConfig {
    public static final String BASE_URL = "http://120.25.241.49:8080/xmatch-0.0.1/";
//    public static final String BASE_URL = "http://172.25.14.162:7979";
//    public static final String BASE_URL = "http://10.0.2.2:7979";

    public  static DefaultConfig defaultConfig;

    private Context context;

    private String stuNo;

    private int depmanagerId;
//    private int departmentId;


    public synchronized static DefaultConfig get(Context context) {
        if (defaultConfig == null) {
            defaultConfig = new DefaultConfig(context);
        }
        return defaultConfig;
    }

    private DefaultConfig(Context context) {
        this.context = context;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public int getDepmanagerId() {
        return depmanagerId;
    }

    public void setDepmanagerId(int depmanagerId) {
        this.depmanagerId = depmanagerId;
    }
//
//    public int getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(int departmentId) {
//        this.departmentId = departmentId;
//    }
}
