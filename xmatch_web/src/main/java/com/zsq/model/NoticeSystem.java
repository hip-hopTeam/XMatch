package com.zsq.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ThomasNg on 2017/11/13.
 */
@Entity
public class NoticeSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeSystemId;

    @Column(unique = true,nullable = false)
    private long appNoticeId;

    private long userId;

    private int statusOfNotice;//已读为0，未读为1

    public void setNoticeSystemId(Long noticeSystemId) {
        this.noticeSystemId = noticeSystemId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatusOfNotice() {
        return statusOfNotice;
    }

    public void setStatusOfNotice(int statusOfNotice) {
        this.statusOfNotice = statusOfNotice;
    }

    public Long getNoticeSystemId() {
        return noticeSystemId;
    }

    public long getAppNoticeId() {
        return appNoticeId;
    }

    public void setAppNoticeId(long appNoticeId) {
        this.appNoticeId = appNoticeId;
    }

}
