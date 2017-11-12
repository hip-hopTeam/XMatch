package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ActivityApply {

    @Id
    private Long activityApplyId;

    private Long activityId;
    private Long userId;
    private String userName;
    /**
     *  1 已报名 2 已签到
     */
    private int state;
    //报名时间
    private long applyTime;
    //签到时间
    private long signInTime;

    @Generated(hash = 1899094068)
    public ActivityApply(Long activityApplyId, Long activityId, Long userId,
            String userName, int state, long applyTime, long signInTime) {
        this.activityApplyId = activityApplyId;
        this.activityId = activityId;
        this.userId = userId;
        this.userName = userName;
        this.state = state;
        this.applyTime = applyTime;
        this.signInTime = signInTime;
    }

    @Generated(hash = 2141696118)
    public ActivityApply() {
    }

    public Long getActivityApplyId() {
        return activityApplyId;
    }

    public void setActivityApplyId(Long activityApplyId) {
        this.activityApplyId = activityApplyId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
}
