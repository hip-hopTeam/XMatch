package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by _Lines on 2017/11/10.
 */
public interface DepManagerRepository extends PagingAndSortingRepository<DepManager,Long> {
    public DepManager findDepManagerByDepManagerAccount(String depManagerAccount);
}
