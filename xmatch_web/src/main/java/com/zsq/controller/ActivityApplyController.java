package com.zsq.controller;

import com.zsq.model.ActivityApply;
import com.zsq.service.ActivityApplyService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    /*
    根据活动id查找所有的申请
     */
    @RequestMapping("/get")
    public ObjectMessage getActivityApplyByActivityId(@RequestParam long activityId,
    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
    @RequestParam(value = "rows", required = false, defaultValue = "100") int rows) {
        ObjectMessage message = new ObjectMessage();
        Map<String, Object> result = activityApplyService.getActivityApplyByActivityId(activityId, page, rows);
        message.code = (int) result.get("code");
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        if(message.code == WyyResultCode.Companion.getSUCCESS()) {
            message.object = result.get("object");
        }
        return message;
    }
}
