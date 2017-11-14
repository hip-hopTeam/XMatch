package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by coderqiang on 2017/11/8.
 */
@Entity
public class EmailNotice {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emailNoticeId;

    private String title;
    private String content;
    private long createTime;
    //要发送的email地址 json格式[{"userId":"2","emailUrl":"976928202@qq.com"},...]
    private String toEmailUrl;



    public long getEmailNoticeId() {
        return emailNoticeId;
    }

    public void setEmailNoticeId(long emailNoticeId) {
        this.emailNoticeId = emailNoticeId;
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

    public String getToEmailUrl() {
        return toEmailUrl;
    }

    public void setToEmailUrl(String toEmailUrl) {
        this.toEmailUrl = toEmailUrl;
    }
}
