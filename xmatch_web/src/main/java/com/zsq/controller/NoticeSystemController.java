package com.zsq.controller;

import com.zsq.model.NoticeSystem;
import com.zsq.service.AppNoticeService;
import com.zsq.service.NoticeSystemService;
import com.zsq.util.BaseMessage;
import com.zsq.util.ThoResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ThomasNg on 2017/11/13.
 */
@RestController
@RequestMapping("/api/noticeSystem")
public class NoticeSystemController {

    @Autowired
    NoticeSystemService noticeSystemService;

    @RequestMapping("/add")
    public BaseMessage addStatusOfNotice(@RequestParam("appNoticeId")long appNoticeId,
                                         @RequestParam("userId")long userId,
                                         @RequestParam("statusOfNotice")int statusOfNotice) {
        BaseMessage message = new BaseMessage();

        //Json数组转long数组待解决

        message.code = noticeSystemService.addStatusOfNotice(appNoticeId, userId, statusOfNotice);
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/delete")
    public BaseMessage deleteStatusOfNotice(@RequestParam("noticeSystemId")long noticeSystemId) {
        BaseMessage message = new BaseMessage();
        message.code = noticeSystemService.deleteStatusOfNotice(noticeSystemId);
        message.result = ThoResultCode.Companion.getMap().get(message.code);
        return message;
    }
}
