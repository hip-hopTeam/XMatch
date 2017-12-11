package com.zsq.service;

/**
 * Created by ThomasNg on 2017/11/13.
 */
public interface NoticeSystemService {

    public int addStatusOfNotice(long appNoticeId,long userId,int statusOfNotice);

    public int deleteStatusOfNotice(long noticeSystemId);
}
