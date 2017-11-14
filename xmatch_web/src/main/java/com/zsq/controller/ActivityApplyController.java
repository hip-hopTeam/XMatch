package com.zsq.controller;

import com.zsq.model.ActivityApply;
import com.zsq.service.ActivityApplyService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hp on 2017/11/13.
 */
@RestController
@RequestMapping("/api/activityApply")
public class ActivityApplyController {
    @Autowired
    ActivityApplyService activityApplyService;

    @RequestMapping("/add")
    public BaseMessage addActivityApply(@RequestBody ActivityApply activityApply) {
        BaseMessage message = new BaseMessage();
        message.code = activityApplyService.addActivityApply(activityApply);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/signIn")
    public BaseMessage signInActivityApply(@RequestParam long activityApplyId) {
        BaseMessage message = new BaseMessage();
        message.code = activityApplyService.signInActivityApply(activityApplyId);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }
}
