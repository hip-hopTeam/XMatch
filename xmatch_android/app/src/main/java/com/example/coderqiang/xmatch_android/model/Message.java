package com.example.coderqiang.xmatch_android.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author CoderQiang
 *         Created by coderqiang on 2017/11/8.
 *         短信通知
 */
@Entity
public class Message {

    @Id
    private Long messageId;

    private String title;
    private String content;
    private long createTime;
    //通知的人员总数
    private int userSum;

    //需要通知的人员json格式[{"userId":"2","phone":"13110521828"},...]
    private String toUsers;


    //发送状态 1 已保存 2 已发送
    private int state;


    @Generated(hash = 1850695742)
    public Message(Long messageId, String title, String content, long createTime,
            int userSum, String toUsers, int state) {
        this.messageId = messageId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.userSum = userSum;
        this.toUsers = toUsers;
        this.state = state;
    }

    @Generated(hash = 637306882)
    public Message() {
    }


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public int getUserSum() {
        return userSum;
    }

    public void setUserSum(int userSum) {
        this.userSum = userSum;
    }

    public String getToUsers() {
        return toUsers;
    }

    public void setToUsers(String toUsers) {
        this.toUsers = toUsers;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
