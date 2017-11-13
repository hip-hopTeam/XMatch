package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by hp on 2017/11/13.
 */
public interface ActivityRepository extends PagingAndSortingRepository<Activity,Long> {
    public Activity findActivityByActivityId(long activityId);
    public List<Activity> findActivitiesByDepId(long departmentId);
    public List<Activity> findAll();

}
