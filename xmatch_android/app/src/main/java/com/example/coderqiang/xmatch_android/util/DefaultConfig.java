package com.example.coderqiang.xmatch_android.util;

import android.content.Context;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class DefaultConfig {
    public static final String BASE_URL = "http://172.25.69.24:7979";

    public  static DefaultConfig defaultConfig;

    private Context context;

    private String stuNo;


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
}
