package com.zsq.service;

import com.zsq.dto.MemberDto;
import com.zsq.model.DepMember;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
public interface DepMemberManagerService {
    /**
     * 添加部员
     */
    public int addDepMember(DepMember depMember);
    /**
     * 删除部员
     */
    public int deleteDepMember(long depId, long userId);
    /**
     * 修改部员
     */
    public int updateDepMember(DepMember depMember);

    public List<MemberDto> getDepMember(long depId, int state);
}
