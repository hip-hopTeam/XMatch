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
import com.zsq.util.ThoResultCode;
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
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/manager/delete")
    public BaseMessage deleteNotice(@RequestParam(value = "appNoticeId",required = true)long appNoticeId) {
        BaseMessage message = new BaseMessage();
        message.code = appNoticeService.deleteAppNotice(appNoticeId);
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/manager/get_department")
    public ObjectMessage getNotice(@RequestParam(value = "departmentId") long departmentId,
                                   @RequestParam(value = "type",required = false,defaultValue = "0")int type) {
        ObjectMessage message = new ObjectMessage();
        if (type == 0){
            message.object = appNoticeService.getOneDepAllNotices(departmentId);
            message.code = ThoResultCode.Companion.getSUCCESS();
            message.result = ThoResultCode.Companion.getMap().get(message.code);
            return message;
        }else {
            message.object = appNoticeService.getOneDepNoticesByType(departmentId,type);
            message.code = ThoResultCode.Companion.getSUCCESS();
            message.result = ThoResultCode.Companion.getMap().get(message.code);
            return message;
        }
    }

    @RequestMapping("/manager/get_all")
    public ObjectMessage getAllNotice() {
        ObjectMessage message = new ObjectMessage();
        message.object = appNoticeService.getAllDepNotices();
        message.code = ThoResultCode.Companion.getSUCCESS();
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/manager/update")
    public BaseMessage updateAppNotice(@RequestBody AppNotice appNotice) {
        BaseMessage message = new BaseMessage();
        message.code = appNoticeService.updateAppNotice(appNotice);
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }
}
