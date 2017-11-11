package com.zsq.controller;

import com.zsq.model.DepMember;
import com.zsq.service.DepMemberManagerService;
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

    @RequestMapping("/get")
    public ObjectMessage getDepMember(@RequestParam("depId") long depId,
                                      @RequestParam(value = "state", required = false, defaultValue = "0") int state) {
        ObjectMessage message = new ObjectMessage();
        List<DepMember> depMembers;
        if(state==0) {
            depMembers = depMemberManagerService.getDepMember(depId);
        } else {
            depMembers = depMemberManagerService.getDepMember(depId, state);
        }
        if(depMembers.size()<=0) {
            message.code = WyyResultCode.Companion.getDEP_NOT_EXIST();
            message.result = WyyResultCode.Companion.getMap().get(message.code);
            return message;
        }
        message.code = WyyResultCode.Companion.getSUCCESS();
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        message.object = depMembers;
        return message;
    }

}
