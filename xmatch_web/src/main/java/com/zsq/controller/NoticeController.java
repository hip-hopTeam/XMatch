package com.zsq.controller;

/**
 * Created by ThomasNg on 2017/11/9.
 */

import com.zsq.model.AppNotice;
import com.zsq.service.AppNoticeService;
import com.zsq.service.UserService;
import com.zsq.util.BaseMessage;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    AppNoticeService appNoticeService;

    @RequestMapping("/manager/add")
    public BaseMessage addNotice(@RequestBody AppNotice appNotice) {
        BaseMessage message = new BaseMessage();
        message.code = appNoticeService.addAppNotice(appNotice);
        message.result = ResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/manager/delete")
    public BaseMessage deleteNotice(@RequestParam long appNoticeId) {
        BaseMessage message = new BaseMessage();
        message.code = appNoticeService.deleteAppNotice(appNoticeId);
        message.result = ResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/manager/get")
    public ObjectMessage getNotice(@RequestParam(value = "departmentId",required = false,defaultValue = "0") long departmentId,
                                   @RequestParam(value = "type",required = false,defaultValue = "0")int type) {
        ObjectMessage message = new ObjectMessage();
        if(departmentId == 0){

        }
        else if (type == 0){

        }
        return message;
    }
}
