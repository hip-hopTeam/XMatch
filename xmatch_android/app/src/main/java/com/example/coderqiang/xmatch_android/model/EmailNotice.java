package com.example.coderqiang.xmatch_android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EmailNotice {

    @Id
    private Long emailNoticeId;

    private String title;
    private String content;
    private long createTime;
    //要发送的email地址 json格式[{"userId":"2","emailUrl":"976928202@qq.com"},...]
    private String toEmailUrl;


    @Generated(hash = 772870656)
    public EmailNotice(Long emailNoticeId, String title, String content,
            long createTime, String toEmailUrl) {
        this.emailNoticeId = emailNoticeId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.toEmailUrl = toEmailUrl;
    }

    @Generated(hash = 508843060)
    public EmailNotice() {
    }


    public Long getEmailNoticeId() {
        return emailNoticeId;
    }

    public void setEmailNoticeId(Long emailNoticeId) {
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
