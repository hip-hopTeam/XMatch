package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by coderqiang on 2017/11/11.
 */
@Entity
public class DepMember {

    public static final int STATE_APPLY=1,STATE_OFFICE=2,STATE_RETIRE=3,STATE_REFUSE=4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long depMemberId;
    private long depId;
    private long userId;
    //入部状态 1 申请中 2 部门正式成员 3 退休成员 4 拒绝加入
    private int state;
    private long joinTime;
    /**在部门里的角色 */
    private String role;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getDepMemberId() {
        return depMemberId;
    }

    public void setDepMemberId(long depMemberId) {
        this.depMemberId = depMemberId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
