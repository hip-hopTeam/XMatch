package com.zsq.service;

import com.zsq.model.ActivityApply;

/**
 * Created by hp on 2017/11/13.
 */
public interface ActivityApplyService {
    public int addActivityApply(ActivityApply activityApply);
    public int signInActivityApply(long activityApplyId);
}
