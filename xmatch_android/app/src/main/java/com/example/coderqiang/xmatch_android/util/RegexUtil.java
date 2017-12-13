package com.example.coderqiang.xmatch_android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class RegexUtil {

    public static String emailRegex="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static boolean isEmpty(String content) {
        if (content == null || content.length() == 0) {
            return true;
        }else {
            return false;
        }

    }

    public static boolean isNum(String content) {
        String regex="^\\d+(\\.\\d+)?$";//正数负数和小数
        if (content.matches(regex)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isPhone(String content) {
        String regex="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        if (content.matches(regex)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isEmail(String content) {
        if (content.matches(emailRegex)){
            return true;
        }else {
            return false;
        }
    }

    public static void showToast(Context context, String content) {
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }

    public static void main(String[] args)throws Exception {
        System.out.println(isNum("0.5"));
    }




}
