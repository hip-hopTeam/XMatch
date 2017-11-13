package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by hp on 2017/11/13.
 */

public interface ActivityApplyRepository extends PagingAndSortingRepository<ActivityApply,Long> {
    public ActivityApply findActivityApplyByActivityIdAndUserId(long activityApplyId,long userId);
}
