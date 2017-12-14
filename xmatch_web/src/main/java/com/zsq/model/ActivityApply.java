package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id

        ;

/**
 * @author CoderQiang
 *         Created by coderqiang on 2017/11/8.
 *         活动报名/签到表
 */
@Entity
public class ActivityApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activityApplyId;

    private long activityId;
    private long userId;
    private String userName;
    /**
     *  1 已报名 2 已签到
     */
    private int state;
    //报名时间
    private long applyTime;
    //签到时间
    private long signInTime;
    //经度
    private double lon;
    //纬度
    private double lat;
    //签到距离
    private double distance;

    public long getActivityApplyId() {
        return activityApplyId;
    }

    public void setActivityApplyId(long activityApplyId) {
        this.activityApplyId = activityApplyId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(long applyTime) {
        this.applyTime = applyTime;
    }

    public long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(long signInTime) {
        this.signInTime = signInTime;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}