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
        appNotice.setCreateTime(System.currentTimeMillis());
        repository.save(appNotice);
        return ThoResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteAppNotice(long appNoticeId) {
        repository.delete(appNoticeId);
        return ThoResultCode.Companion.getSUCCESS();
    }

    @Override
    public AppNotice getAppNotice(long departmentId,int type) {
        AppNotice appNotice= repository.findAppNoticeByDepartmentIdAndType(departmentId,type);
        return appNotice;
    }
}

