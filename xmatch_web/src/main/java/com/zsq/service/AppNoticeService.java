package com.zsq.service;

import com.zsq.model.AppNotice;

public interface AppNoticeService {
    /**
     * 添加通知
     * @param appNotice
     * @return
     */
    public int addAppNotice(AppNotice appNotice);
    /**
     * 删除通知
     * @param appNoticeId
     * @return
     */
    public int deleteAppNotice(long appNoticeId);
    /**
     * 查找通知
     * @param departmentId,type 部门ID，群发类型
     * @return
     */
    public AppNotice getAppNotice(long departmentId,int type);
}