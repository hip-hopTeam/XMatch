package com.zsq.service;

import com.zsq.model.AppNotice;

import java.util.List;

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
     * 查找部门所有通知
     * @param departmentId 部门ID
     * @return
     */
    public List<AppNotice> getOneDepAllNotices(long departmentId);
    /**
     * 查找用户所在部门所有通知
     * @param userId 用户ID
     * @return
     */
    public List<AppNotice> getUserAllNotices(long userId,int page,int rows);
    /**
     * 查找部门类型通知
     * @param departmentId,type 部门ID,类型
     * @return
     */
    public List<AppNotice> getOneDepNoticesByType(long departmentId,int type);
    /**
     * 查找所有部门通知
     * @param
     * @return
     */
    public List<AppNotice> getAllDepNotices();
    /**
     * 修改通知
     * @param appNotice
     * @return
     */
    public int updateAppNotice(AppNotice appNotice);
}