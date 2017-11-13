package com.zsq.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.zsq.dto.MemberDto;
import com.zsq.model.ChildDepartment;
import com.zsq.model.DepMember;
import com.zsq.model.Department;
import com.zsq.service.DepMemberManagerService;
import com.zsq.service.DepartmentService;
import com.zsq.util.BaseMessage;
import com.zsq.util.ObjectMessage;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11/011.
 */
@RestController
@RequestMapping("/api/depMemberManage")
public class DepMemberManageController {

    @Autowired
    DepMemberManagerService depMemberManagerService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/add")
    public BaseMessage addDepMember(@RequestBody DepMember depMember) {
        BaseMessage message = new BaseMessage();
        message.code = depMemberManagerService.addDepMember(depMember);
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/delete")
    public BaseMessage deleteDepMember(@RequestParam("depId") long depId,
                                       @RequestParam("userId") long userId) {
        BaseMessage message = new BaseMessage();
        message.code = depMemberManagerService.deleteDepMember(depId, userId);
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/update")
    public BaseMessage updateDepMember(@RequestBody DepMember depMember) {
        BaseMessage message = new BaseMessage();
        message.code = depMemberManagerService.updateDepMember(depMember);
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/get")
    public ObjectMessage getDepMember(@RequestParam("depId") long depId,
                                      @RequestParam(value = "state", required = false, defaultValue = "0") int state) {
        ObjectMessage message = new ObjectMessage();
        Department department = departmentService.getDepartment(depId);
        if(department==null) {
            message.code = WyyResultCode.Companion.getDEP_NOT_EXIST();
            message.result = WyyResultCode.Companion.getMap().get(message.code);
            return message;
        }
        List<MemberDto> depMembers;
        depMembers = depMemberManagerService.getDepMember(depId, state);
        message.code = WyyResultCode.Companion.getSUCCESS();
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }

}
