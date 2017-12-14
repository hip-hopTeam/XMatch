package com.example.coderqiang.xmatch_android.util.jwt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class UtilString {
    public static boolean isFloatNum(String s) {
        return Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$").matcher(s).matches();
    }

    public static boolean isIntNum(String s) {
        return Pattern.compile("^[-+]?[0-9]").matcher(s).matches();
    }

    public static Date strToDate(String style, String date) {
        try {
            return new SimpleDateFormat(style).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String dateToStr(String style, Date date) {
        return new SimpleDateFormat(style).format(date);
    }

    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    public static String dealHtml(String htmlStr) {
        return htmlStr.replaceAll("<BR><BR><BR><BR>", "\n").replaceAll("<BR><BR><BR>", "\n").replaceAll("<BR><BR>", "\n").replaceAll("<BR>", "\n");
    }

    public static double getLength(String s) {
        double valueLength = 0.0d;
        String chinese = "[一-龥]";
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).matches(chinese)) {
                valueLength += 1.0d;
            }
        }
        return Math.ceil(valueLength);
    }

    public static String getSeed(String xh, String date) {
        String[] dateStr = new String[]{date.substring(0, 4), date.substring(4, 6), date.substring(6, 8), date.substring(8, 10), date.substring(10, 12), date.substring(12, 14)};
        String retStr = "";
        int i = 0;
        for (int m = (Integer.parseInt(xh) % 63) + 1; m > 0; m >>= 1) {
            if (m % 2 != 0) {
                retStr = dateStr[i] + retStr;
            }
            i++;
        }
        return retStr;
    }
}
