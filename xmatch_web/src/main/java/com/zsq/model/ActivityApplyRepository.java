package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by hp on 2017/11/13.
 */

public interface ActivityApplyRepository extends PagingAndSortingRepository<ActivityApply,Long> {
    public ActivityApply findActivityApplyByActivityIdAndUserId(long activityApplyId,long userId);
    @Query("select a from ActivityApply a where a.activityId=?1")
    public Page<ActivityApply> findActivityAppliesByActivityId(long activityId, Pageable pageable);
}
