package com.zsq.controller;

import com.zsq.model.Activity;
import com.zsq.service.ActivityService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

/**
 * Created by hp on 2017/11/13.
 */

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityService activityservice;

    @RequestMapping("/add")
    public BaseMessage addActivity(@RequestBody Activity activity) {
        BaseMessage message = new BaseMessage();
        message.code = activityservice.addActivity(activity);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/get")
    public ObjectMessage getActivity(@RequestParam long activityId,
                                     @RequestParam long departmentId,
                                     @RequestParam int type) {
        ObjectMessage message = new ObjectMessage();
        List<Activity> activityList = new ArrayList<>();
        activityList = activityservice.getActivity(activityId,departmentId,type);
        message.code = LsyResultCode.Companion.getSUCCESS();
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        message.object = activityList;
        return message;
    }

    @RequestMapping("/update")
    public BaseMessage updateActivity(@RequestBody Activity activity) {
        BaseMessage message = new BaseMessage();
        message.code = activityservice.updateActivity(activity);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

}
