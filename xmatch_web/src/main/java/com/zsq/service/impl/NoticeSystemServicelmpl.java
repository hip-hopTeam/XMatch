package com.zsq.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zsq.model.AppNoticeRepository;
import com.zsq.model.NoticeSystem;
import com.zsq.model.NoticeSystemRepository;
import com.zsq.service.NoticeSystemService;
import com.zsq.util.ThoResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by ThomasNg on 2017/11/13.
 */
@Service
@EnableTransactionManagement
@Transactional
public class NoticeSystemServicelmpl implements NoticeSystemService{

    @Autowired
    NoticeSystemRepository repository;

    @Override
    public int addStatusOfNotice(long appNoticeId,long userId,int statusOfNotice) {
        NoticeSystem noticeSystem = new NoticeSystem();
        noticeSystem.setAppNoticeId(appNoticeId);
        noticeSystem.setUserId(userId);
        noticeSystem.setStatusOfNotice(statusOfNotice);
        repository.save(noticeSystem);
        return ThoResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteStatusOfNotice(long noticeSystemId) {
        NoticeSystem isExistNotice = repository.findOne(noticeSystemId);
        if (isExistNotice == null) {
            return ThoResultCode.Companion.getNOTICE_NOT_EXIST();
        }
        repository.delete(noticeSystemId);
        return ThoResultCode.Companion.getSUCCESS();
    }



}
