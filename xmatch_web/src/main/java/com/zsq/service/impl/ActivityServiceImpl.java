package com.zsq.service.impl;

import com.zsq.model.Activity;
import com.zsq.model.ActivityRepository;
import com.zsq.model.Department;
import com.zsq.model.DepartmentRepository;
import com.zsq.service.ActivityService;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/11/13.
 */
@Service
@EnableTransactionManagement
@Transactional
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    ActivityRepository repository;
    @Autowired
    DepartmentRepository depRepository;

    @Override
    public int addActivity(Activity activity) {
        activity.setCreateTime(System.currentTimeMillis());
        activity.setSignIn(0);
        activity.setApplyNum(0);
        repository.save(activity);
        Department dep = depRepository.findOne(activity.getDepId());
        dep.setActivityNum(dep.getActivityNum()+1);
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateActivity(Activity activity) {
        Activity resActivity = repository.findOne(activity.getActivityId());
        if(resActivity == null) {
            return LsyResultCode.Companion.getACTIVITY_NOT_EXIST();
        }
        resActivity.setAddress(activity.getAddress());
        resActivity.setContent(activity.getContent());
        resActivity.setStartTime(activity.getStartTime());
        resActivity.setEndTime(activity.getEndTime());
        resActivity.setManagerPhone(activity.getManagerPhone());
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteActivity(long activityId) {
        repository.delete(activityId);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    /**
     * type 0 查询全部活动 1 条件查询
     * activityId -1 按照部门查找活动
     * departmentId -1 按照活动Id查找活动
     * */
    public List<Activity> getActivity(long activityId,long departmentId, int type) {
        List<Activity> activities = new ArrayList<>();
        if(type == 0) {
            activities = repository.findAll();
        } else {
            if(departmentId == -1) {
                activities.add(repository.findActivityByActivityId(activityId));
            } else if(activityId == -1){
                activities = repository.findActivitiesByDepId(departmentId);
            }
        }
        return activities;
    }

    @Override
    public List<Activity> getAllActivity() {
        List<Activity> activities = repository.findAll();
        return activities;
    }

    @Override
    public int addActivityImage(long activityId, String url) {
        Activity activity = repository.findOne(activityId);
        activity.setImageUrl(url);
        return LsyResultCode.Companion.getSUCCESS();
    }
}
