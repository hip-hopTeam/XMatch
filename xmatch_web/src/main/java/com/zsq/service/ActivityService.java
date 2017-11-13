package com.zsq.service;

import com.zsq.model.Activity;

import java.util.List;

/**
 * Created by hp on 2017/11/13.
 */
public interface ActivityService {
    /**
     * 添加活动
     * @param activity
     * @return
     */
    public int addActivity(Activity activity);

    public int updateActivity(Activity activity);

    public List<Activity> getActivity(long activityId,long departmentId, int type);


}
