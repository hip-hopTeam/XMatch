package com.zsq.service.impl;

import com.zsq.dto.MemberDto;
import com.zsq.model.*;
import com.zsq.service.DepMemberManagerService;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
@Service
public class DepMemberManagerServiceImpl implements DepMemberManagerService {

    @Autowired
    DepMemberRepository repository;
    @Autowired
    UserRepository userRepository;

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

    @Override
    public List<MemberDto> getDepMember(long depId, int state) {
        List<MemberDto> memberDtos=new ArrayList<>();
        List<DepMember> depMembers;
        if(state==0) {
            depMembers= repository.findDepMembersByDepId(depId);
        } else {
            depMembers=repository.findDepMembersByDepIdAndState(depId,state);
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
}
