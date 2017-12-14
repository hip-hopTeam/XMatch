package com.zsq.service.impl;

import com.zsq.model.Activity;
import com.zsq.model.ActivityRepository;
import com.zsq.model.Department;
import com.zsq.model.DepartmentRepository;
import com.zsq.service.ActivityService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author CoderQiang
 * Created by hp on 2017/11/13.
 */
@Service
@EnableTransactionManagement
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    ActivityRepository repository;
    @Autowired
    DepartmentRepository depRepository;

    /**
     * Add activity
     * @param activity
     * @return activityId
     */
    @javax.transaction.Transactional
    @Override
    public ObjectMessage addActivity(Activity activity) {
        activity.setCreateTime(System.currentTimeMillis());
        activity.setSignIn(0);
        activity.setApplyNum(0);
        repository.save(activity);
        Department dep = depRepository.findOne(activity.getDepId());
        dep.setActivityNum(dep.getActivityNum()+1);
        ObjectMessage message = new ObjectMessage();
        message.code=LsyResultCode.Companion.getSUCCESS();
        message.object=activity.getActivityId();
        return message;
    }

    /**
     * Update activity
     * @param activity
     * @return resultCode
     */

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

    /**
     * Delete activity by activityId
     * @param activityId
     * @return resultCode
     */
    @Override
    public int deleteActivity(long activityId) {
        Activity activity = repository.findOne(activityId);
        Department department = depRepository.findOne(activity.getDepId());
        department.setActivityNum(department.getActivityNum()>0?department.getActivityNum()-1:0);
        repository.delete(activity);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    /**
     * type 0 query all activities 1 Criteria queries
     * activityId -1 Search activities according to Department
     * departmentId -1 Search activities according to active Id
     * */
    public List<Activity> getActivity(long activityId,long departmentId, int type,int page,int rows) {
        List<Activity> activities = new ArrayList<>();
        if(type == 0) {
            Sort sort=new Sort(Sort.Direction.DESC,"createTime");
            activities = repository.findAllAcitivities(new PageRequest(page,rows,sort)).getContent();
        } else {
            if(departmentId == -1) {
                activities.add(repository.findActivityByActivityId(activityId));
            } else if(activityId == -1){
                Sort sort=new Sort(Sort.Direction.DESC,"createTime");
                activities = repository.findActivitiesByDepId(departmentId,new PageRequest(page,rows,sort)).getContent();
            }
        }
        return activities;
    }

    /**
     * type 0 query all activities no parameter
     * @return List<Activity>
     */
    @Override
    public List<Activity> getAllActivity(int page, int rows) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        List<Activity> activities = repository.findAllAcitivities(new PageRequest(page,rows,sort)).getContent();
        return activities;
    }

    /**
     * Add activity pictures
     * @param activityId
     * @param url
     * @return resultCode
     */

    @Override
    public int addActivityImage(long activityId, String url) {
        Activity activity = repository.findOne(activityId);
        activity.setImageUrl(url);
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int signInActivity(Activity activity) {
        Activity resActivity = repository.findOne(activity.getActivityId());
        if(resActivity == null) {
            return LsyResultCode.Companion.getACTIVITY_NOT_EXIST();
        }
        resActivity.setSignState(2);
        resActivity.setSignStr(activity.getSignStr());
        return LsyResultCode.Companion.getSUCCESS();
    }


}
