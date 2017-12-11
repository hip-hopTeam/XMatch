package com.zsq.controller;

import com.zsq.model.Activity;
import com.zsq.service.ActivityService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public ObjectMessage addActivity(@RequestBody Activity activity) {
        ObjectMessage message = new ObjectMessage();
        message = activityservice.addActivity(activity);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/image/add")
    public BaseMessage addActivityImage(
            @RequestParam("file")MultipartFile file,
            @RequestParam("activityId")long activityId,
            HttpServletRequest request) {
        BaseMessage message = new BaseMessage();

        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String prefix=file.getOriginalFilename().replaceAll(".*(\\.(.*))","$1");
        String url="/image_activity/"+activityId+prefix;
        File avator = new File(rootPath+"/static"+url);
        if (!avator.getParentFile().exists()) {
            avator.getParentFile().mkdirs();
        }
        try {
            file.transferTo(avator);
        } catch (IOException e) {
            e.printStackTrace();
            message.code = LsyResultCode.Companion.getERROR();
            message.result= LsyResultCode.Companion.getMap().get(message.code);
            return message;
        }
        message.code = activityservice.addActivityImage(activityId, url);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }


    @RequestMapping("/get")
    public ObjectMessage getActivity(@RequestParam int type,
                                     @RequestParam(required = false,defaultValue = "-1") long activityId,
                                     @RequestParam(required = false,defaultValue = "-1") long departmentId,
                                     @RequestParam(required = false,defaultValue = "0") int page,
                                     @RequestParam(required = false,defaultValue = "10") int rows) {
        ObjectMessage message = new ObjectMessage();
        List<Activity> activityList = new ArrayList<>();
        activityList = activityservice.getActivity(activityId,departmentId,type,page,rows);
        message.code = LsyResultCode.Companion.getSUCCESS();
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        message.object = activityList;
        return message;
    }

    @RequestMapping("/getAll")
    public ObjectMessage getAllActivity() {
        ObjectMessage message = new ObjectMessage();
        List<Activity> activityList = activityservice.getAllActivity();
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


    @RequestMapping("/delete")
    public BaseMessage deleteActivity(@RequestParam("activityId") long activityId) {
        BaseMessage message = new BaseMessage();
        message.code = activityservice.deleteActivity(activityId);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

}
