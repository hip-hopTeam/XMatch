package com.zsq.service.impl;

import com.zsq.dto.MemberDto;
import com.zsq.model.*;
import com.zsq.service.DepMemberManagerService;
import com.zsq.util.ResultCode;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/11/11/011.
 */
@Service
@EnableTransactionManagement
@Transactional
public class DepMemberManagerServiceImpl implements DepMemberManagerService {

    @Autowired
    DepMemberRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public int addDepMember(DepMember depMember) {
        User isExistUser = userRepository.findOne(depMember.getUserId());
        if(isExistUser==null) {
            return WyyResultCode.Companion.getUSER_NOT_EXIST();
        }
        DepMember isExistDepMember = repository.findDepMemberByUserIdAndDepId(depMember.getUserId(),
                depMember.getDepId());
        if(isExistDepMember!=null&&isExistDepMember.getDepId()>=0) {
            return WyyResultCode.Companion.getDEP_MEMBER_EXIST();
        }
        Department department = departmentRepository.findOne(depMember.getDepId());
        if (department == null) {
            return ResultCode.Companion.getDEPARTMENT_NOT_EXIST();
        }
        depMember.setJoinTime(System.currentTimeMillis());
        depMember.setState(DepMember.STATE_APPLY);
        repository.save(depMember);
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteDepMember(long depId, long userId) {
        DepMember isExistDepMember = repository.findDepMemberByUserIdAndDepId(userId, depId);
        Department isExistDepartment = departmentRepository.getDepartmentByDepartmentId(depId);
        if(isExistDepMember==null) {
            return WyyResultCode.Companion.getDEP_MEMBER_NOT_EXIST();
        }
        int num = isExistDepartment.getMemberNum()-1;
        if(num<0) num=0;
        isExistDepartment.setMemberNum(num);
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
        isExistDepMember.setState(depMember.getState());
        isExistDepMember.setRole(depMember.getRole());
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    /*
    按照部门Id和部员状态查找部员
     */
    public List<MemberDto> getDepMember(long depId, int state, int page, int rows) {
        List<MemberDto> memberDtos=new ArrayList<>();
        List<DepMember> depMembers;
        Sort sort = new Sort(Sort.Direction.ASC, "joinTime");
        if(state==0) {
            depMembers=repository.findDepMembersByDepId(depId, new PageRequest(page, rows, sort)).getContent();
        } else {
            depMembers=repository.findDepMembersByDepIdAndState(depId, state, new PageRequest(page, rows, sort)).getContent();
        }
        for (DepMember depMember:depMembers){
            MemberDto memberDto=new MemberDto();
            BeanUtils.copyProperties(depMember,memberDto);
            User user=userRepository.findOne(depMember.getUserId());
            BeanUtils.copyProperties(user,memberDto);
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }

    @Override
    public int handleMemberResult(long depMemberId, int state) {
        DepMember depMember = repository.findOne(depMemberId);
        if (depMember == null) {
            return ResultCode.Companion.getTARGET_NOT_EXIST();
        }
        Department department = departmentRepository.findOne(depMember.getDepId());
        if (state == DepMember.STATE_OFFICE ) {
            System.out.println("addMember");
            department.setMemberNum(department.getMemberNum() + 1);
        }
        depMember.setState(state);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    public List<Long> getDepartments(long userId) {
        List<DepMember> depMembers = repository.findOfficeDepMemberByUserId(userId);
        List<Long> depIds=depMembers.stream().map( depMember -> depMember.getDepId()).collect(Collectors.toList());
        System.out.println("depIds:"+depIds.size());
        return depIds;
    }
}
