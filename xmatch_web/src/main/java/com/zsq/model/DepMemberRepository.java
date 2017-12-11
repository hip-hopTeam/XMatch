package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
public interface DepMemberRepository extends PagingAndSortingRepository<DepMember,Long> {

    /**
     * 根据部门id查找部员
     */
    @Query("select d from DepMember d where d.depId=?1")
    public List<DepMember> findDepMembersByDepId(long depId);

    /**
     * 根据部门id和部员状态查找部员
     */
    @Query("select d from DepMember d where d.depId=?1 and d.state=?2")
    public Page<DepMember> findDepMembersByDepIdAndState(long depId, int state,Pageable pageable);

    //@Query("select d")
    public DepMember findDepMemberByUserId(long userId);

    public DepMember findDepMemberByUserIdAndDepId(long userId, long depId);
}