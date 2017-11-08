package com.zsq.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/8.
 * 课程表
 */
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    /**
     * 课程名字
     */
    private String name;
    /**
     * 课程的开始节数 1-12
     */
    private int startTime;
    /**
     * 课程的截止节数 1-12
     */
    private int endTime;
    /**
     * 课程的开始周数 1-20
     */
    private int startWeek;
    /**
     * 课程的节数周数 1-20
     */
    private int endWeek;
    //是否双周
    private boolean isDouble=true;
    //是否单周
    private boolean isSingle=true;
    //星期几
    private int weekend;
    //年份
    private int year;

    //学期 1 或 2
    private int xuenian;


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public int getWeekend() {
        return weekend;
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getXuenian() {
        return xuenian;
    }

    public void setXuenian(int xuenian) {
        this.xuenian = xuenian;
    }
}
