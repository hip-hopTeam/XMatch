package com.zsq.service.impl;

import com.zsq.model.AppNotice;
import com.zsq.model.AppNoticeRepository;
import com.zsq.service.AppNoticeService;
import com.zsq.util.ThoResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by ThomasNg on 2017/11/10.
 */
@Service
@EnableTransactionManagement
@Transactional
public  class AppNoticeServiceImpl implements AppNoticeService{

    @Autowired
    AppNoticeRepository repository;


    @Override
    public int addAppNotice(AppNotice appNotice) {
        if (appNotice.getContent() == null || appNotice.getTitle() == null) {
            return ThoResultCode.Companion.getTITLE_OR_CONTENT_IS_NULL();
        }
        appNotice.setCreateTime(System.currentTimeMillis());
        repository.save(appNotice);
        return ThoResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteAppNotice(long appNoticeId) {
        AppNotice appNotice = repository.findOne(appNoticeId);
        if (appNotice == null){
            return ThoResultCode.Companion.getNOTICE_NOT_EXIST();
        }
        repository.delete(appNoticeId);
        return ThoResultCode.Companion.getSUCCESS();
    }

    @Override
    public List<AppNotice> getOneDepAllNotices(long departmentId) {
        List<AppNotice> oneDepAppNotices = repository.findAppNoticesByDepartmentId(departmentId);
        return oneDepAppNotices;
    }

    @Override
    public  List<AppNotice> getOneDepNoticesByType(long departmentId,int type) {
        List<AppNotice> oneDepTypeNotices = repository.findAppNoticesByDepartmentIdAndType(departmentId,type);
        Collections.sort(oneDepTypeNotices, (o1, o2) -> {
            if (o1.getCreateTime() > o2.getCreateTime()) {
                return -1;
            } else if (o1.getCreateTime() < o2.getCreateTime()) {
                return 1;
            }
            return 0;
        });
        return oneDepTypeNotices;
    }

    @Override
    public List<AppNotice> getAllDepNotices() {
        List<AppNotice> allDepNotices = repository.getAll();
        Collections.sort(allDepNotices, (o1, o2) -> {
            if (o1.getCreateTime() > o2.getCreateTime()) {
                return -1;
            } else if (o1.getCreateTime() < o2.getCreateTime()) {
                return 1;
            }
            return 0;
        });
        return allDepNotices;
    }

    @Override
    public int updateAppNotice(AppNotice appNotice) {
        AppNotice resAppNotice = repository.findOne(appNotice.getAppNoticeId());
        if (resAppNotice == null) {
            return ThoResultCode.Companion.getNOTICE_NOT_EXIST();
        }else {
            resAppNotice.setContent(appNotice.getContent());
            resAppNotice.setCreateTime(appNotice.getCreateTime());
            resAppNotice.setDepartmentId(appNotice.getDepartmentId());
            resAppNotice.setTitle(appNotice.getTitle());
            resAppNotice.setType(appNotice.getType());
            return ThoResultCode.Companion.getSUCCESS();
        }
    }
}

