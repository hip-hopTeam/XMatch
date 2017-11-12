package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Attendance {

    @Id
    private Long attendanceId;

    private Long userId;
    private Long departmentId;

    //值班的开始节数 1-8
    private int startTime;
    //值班的截止节数 1-8
    private int endTime;
    //值班的星期几 1-5
    private int weekend;
    //值班的第几周 1-20
    private int week;
    //算法生成时间
    private Long createTime;


    @Generated(hash = 317506568)
    public Attendance(Long attendanceId, Long userId, Long departmentId,
            int startTime, int endTime, int weekend, int week, Long createTime) {
        this.attendanceId = attendanceId;
        this.userId = userId;
        this.departmentId = departmentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekend = weekend;
        this.week = week;
        this.createTime = createTime;
    }

    @Generated(hash = 812698609)
    public Attendance() {
    }


    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public int getWeekend() {
        return weekend;
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
