package com.zsq.service;

import com.zsq.model.ActivityApply;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/11/13.
 */
public interface ActivityApplyService {
    public int addActivityApply(ActivityApply activityApply);
    public int signInActivityApply(long activityApplyId);
    public Map<String, Object> getActivityApplyByActivityId(long activityId, int page, int rows);
}
