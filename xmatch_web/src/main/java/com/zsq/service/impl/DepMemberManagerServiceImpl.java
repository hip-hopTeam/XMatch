package com.zsq.service.impl;

import com.zsq.model.*;
import com.zsq.service.DepMemberManagerService;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public int addDepMember(DepMember depMember) {
        Department isExistDepartment = departmentRepository.findOne(depMember.getDepId());
        if(isExistDepartment==null) {
            return WyyResultCode.Companion.getDEP_NOT_EXIST();
        }
        User isExistUser = userRepository.findOne(depMember.getUserId());
        if(isExistUser==null) {
            return WyyResultCode.Companion.getUSER_NOT_EXIST();
        }
        DepMember isExistDepMember = repository.findDepMemberByUserIdAndDepId(depMember.getUserId(),
                depMember.getDepId());
        if(isExistDepMember!=null&&isExistDepMember.getDepId()>=0) {
            return WyyResultCode.Companion.getDEP_MEMBER_EXIST();
        }
        depMember.setJoinTime(System.currentTimeMillis());
        repository.save(depMember);
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteDepMember(long depId, long userId) {
        DepMember isExistDepMember = repository.findDepMemberByUserIdAndDepId(userId, depId);
        if(isExistDepMember==null) {
            return WyyResultCode.Companion.getDEP_MEMBER_NOT_EXIST();
        }
        repository.delete(isExistDepMember.getDepMemberId());
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateDepMember(DepMember depMember) {
        DepMember isExistDepMember = repository.findDepMemberByUserIdAndDepId(depMember.getUserId(),
                depMember.getDepId());
        if(isExistDepMember==null) {
            return WyyResultCode.Companion.getDEP_MEMBER_NOT_EXIST();
        }
        BeanUtils.copyProperties(depMember, isExistDepMember);
        return WyyResultCode.Companion.getSUCCESS();
    }

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
