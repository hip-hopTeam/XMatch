package com.zsq.service;

import com.zsq.model.Activity;
import com.zsq.util.BaseMessage;
import com.zsq.util.ObjectMessage;

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
    public ObjectMessage addActivity(Activity activity);

    public int updateActivity(Activity activity);

    public int deleteActivity(long activityId);

    public List<Activity> getActivity(long activityId,long departmentId, int type);

    public List<Activity> getAllActivity();


    public int addActivityImage(long activity,String url);

}
