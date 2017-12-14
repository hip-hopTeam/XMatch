package com.example.coderqiang.xmatch_android.util.jwt;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

public class UtilSystem {
    public static String getHandSetInfo(Context context) {
        return "手机型号:" + getPhoneModel() + ",SDK版本:" + getSdkVersion() + ",系统版本:" + getOsVersion() + ",软件版本编码:" + getAppVersionCode(context) + ",软件版本名称:" + getAppVersionName(context);
    }

    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getAppVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    public static int getAppVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            int i=16384;
            return context.getPackageManager().getPackageInfo(context.getPackageName(),i);
        } catch (Exception e) {
            e.printStackTrace();
            return pi;
        }
    }
}
