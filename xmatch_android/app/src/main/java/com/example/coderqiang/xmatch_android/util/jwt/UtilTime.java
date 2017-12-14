package com.example.coderqiang.xmatch_android.util.jwt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UtilTime {
    public static int daysBetween(Date date1, Date date2) throws ParseException {
        return ((int) ((date2.getTime() - date1.getTime()) / 86400000)) + 1;
    }

    public static int weekBetween(Date date1, Date date2) throws Exception {
        int disDay = daysBetween(date1, date2);
        return (disDay / 7) + (disDay % 7 == 0 ? 0 : 1);
    }

    public static int getCurWeek(String kssjStr) throws Exception {
        return weekBetween(new SimpleDateFormat("yyyy-MM-dd").parse(kssjStr), new Date());
    }

    public static int getCurWeekday(String kssjStr) throws Exception {
        int disDay = daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(kssjStr), new Date());
        return disDay % 7 == 0 ? 7 : disDay % 7;
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }

    public static String getCurrentTimeStr() {
        return getCurrentTime("yyyyMMddHHmmss");
    }

    public static String getCurrentTimeStr1() {
        return getCurrentTime("yyyy-MM-dd");
    }
}
