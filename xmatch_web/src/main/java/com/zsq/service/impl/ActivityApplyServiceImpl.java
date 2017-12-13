package com.zsq.service.impl;

import com.zsq.model.Activity;
import com.zsq.model.ActivityApply;
import com.zsq.model.ActivityApplyRepository;
import com.zsq.model.ActivityRepository;
import com.zsq.service.ActivityApplyService;
import com.zsq.util.LsyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

/**
 * Created by hp on 2017/11/13.
 */

@Service
@EnableTransactionManagement
@Transactional
public class ActivityApplyServiceImpl implements ActivityApplyService{
    @Autowired
    ActivityApplyRepository repository;
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public int addActivityApply(ActivityApply activityApply) {
         ActivityApply isExistActivityApply = repository.findActivityApplyByActivityIdAndUserId(activityApply.getActivityId(),
                 activityApply.getUserId());
         if(isExistActivityApply != null && isExistActivityApply.getActivityApplyId() >= 0) {
             return LsyResultCode.Companion.getACTIVITY_APPLY_EXIST();
         }
         activityApply.setApplyTime(System.currentTimeMillis());
         activityApply.setState(1);
         Activity activity = activityRepository.findOne(activityApply.getActivityId());
         activity.setApplyNum(activity.getApplyNum()+1);
         repository.save(activityApply);
         return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int signInActivityApply(long activityApplyId) {
        ActivityApply activityApply = repository.findOne(activityApplyId);
        if(activityApply.getState() == 2) {
            return LsyResultCode.Companion.getACTIVITY_SIGNIN_EXIST();
        }
        Activity activity = activityRepository.findOne(activityApplyId);
        activityApply.setState(2);
        activityApply.setLon(activityApply.getLon());
        activityApply.setLat(activityApply.getLat());
        activityApply.setDistance(activityApply.getDistance());
        activityApply.setSignInTime(System.currentTimeMillis());
        activity.setSignIn(activity.getSignIn()+1);
        return LsyResultCode.Companion.getSUCCESS();
    }

}
