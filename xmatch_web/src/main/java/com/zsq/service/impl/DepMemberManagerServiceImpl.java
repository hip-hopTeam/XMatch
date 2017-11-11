package com.zsq.service.impl;

import com.zsq.model.DepMember;
import com.zsq.model.DepMemberRepository;
import com.zsq.model.UserRepository;
import com.zsq.service.DepMemberManagerService;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
@Service
public class DepMemberManagerServiceImpl implements DepMemberManagerService {

    @Autowired
    DepMemberRepository repository;

    /**
     * 根据部门id查找部员
     *
     * @param depId
     */
    @Override
    public List<DepMember> getDepMember(long depId) {
        List<DepMember> depMembers = repository.findDepMembersByDepId(depId);
        return depMembers;
    }

    /**
     * 根据部门id和部员状态查找部员
     *
     * @param depId
     * @param state
     */
    @Override
    public List<DepMember> getDepMember(long depId, int state) {
        List<DepMember> depMembers = repository.findDepMembersByDepIdAndState(depId, state);
        return depMembers;
    }
}
