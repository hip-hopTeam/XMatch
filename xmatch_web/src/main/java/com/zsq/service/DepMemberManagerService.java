package com.zsq.service;

import com.zsq.model.DepMember;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
public interface DepMemberManagerService {
    /**
     * 根据部门id查找部员
     */
    public List<DepMember> getDepMember(long depId);

    /**
     * 根据部门id和部员状态查找部员
     */
    public List<DepMember> getDepMember(long depId, int state);
}
