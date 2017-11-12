package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AppNotice {

    @Id
    private Long appNoticeId;

    private String title;

    private String content;
    private long createTime;

    //消息类型 1 部门内部 2 全校
    private int type;

    //发送消息的部门
    private long departmentId;



    @Generated(hash = 319174031)
    public AppNotice(Long appNoticeId, String title, String content,
            long createTime, int type, long departmentId) {
        this.appNoticeId = appNoticeId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
        this.departmentId = departmentId;
    }

    @Generated(hash = 25845735)
    public AppNotice() {
    }



    public Long getAppNoticeId() {
        return appNoticeId;
    }

    public void setAppNoticeId(Long appNoticeId) {
        this.appNoticeId = appNoticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}

