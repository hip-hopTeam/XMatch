package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Course {

    @Id
    private Long courseId;

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



    @Generated(hash = 737378872)
    public Course(Long courseId, String name, int startTime, int endTime,
            int startWeek, int endWeek, boolean isDouble, boolean isSingle,
            int weekend, int year, int xuenian) {
        this.courseId = courseId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.isDouble = isDouble;
        this.isSingle = isSingle;
        this.weekend = weekend;
        this.year = year;
        this.xuenian = xuenian;
    }

    @Generated(hash = 1355838961)
    public Course() {
    }



    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
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

    public boolean getIsSingle() {
        return this.isSingle;
    }

    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    public boolean getIsDouble() {
        return this.isDouble;
    }

    public void setIsDouble(boolean isDouble) {
        this.isDouble = isDouble;
    }


}
