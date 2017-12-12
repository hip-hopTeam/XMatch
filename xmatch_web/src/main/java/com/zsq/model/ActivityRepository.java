package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by hp on 2017/11/13.
 */
public interface ActivityRepository extends PagingAndSortingRepository<Activity,Long> {

    @Query("SELECT a FROM Activity a where a.activityId=:activityId")
    public Activity findActivityByActivityId(@Param("activityId") long activityId);

    @Query("SELECT a FROM Activity a where a.depId=:depId")
    public Page<Activity> findActivitiesByDepId(@Param(value = "depId") long departmentId, Pageable pageable);

    @Query("SELECT a FROM Activity a")
    public Page<Activity> findAllAcitivities(Pageable pageable);

}
