package com.zsq.model;

import javax.persistence.*;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/8.
 * app通知
 */

@Entity
public class AppNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appNoticeId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private long createTime;

    //消息类型 1 部门内部 2 全校
    private int type;

    //发送消息的部门
    private long departmentId;

    private String deparmentName;

    public String getDeparmentName() {
        return deparmentName;
    }

    public void setDeparmentName(String deparmentName) {
        this.deparmentName = deparmentName;
    }

    public long getAppNoticeId() {
        return appNoticeId;
    }

    public void setAppNoticeId(long appNoticeId) {
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

