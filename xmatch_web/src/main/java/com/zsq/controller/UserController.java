package com.zsq.controller;

import com.zsq.dto.UserDto;
import com.zsq.model.ActivityApply;
import com.zsq.model.User;
import com.zsq.service.ActivityApplyService;
import com.zsq.service.UserService;
import com.zsq.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ActivityApplyService activityApplyService;

    @RequestMapping("/add")
    public BaseMessage addUser(@RequestBody  User user) {
        BaseMessage message = new BaseMessage();
        message.code=userService.addUser(user);
        message.result = ResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/update")
    public ObjectMessage updateUser(@RequestBody User user) {
        ObjectMessage message = new ObjectMessage();
        int result= userService.updateUser(user);
        message.code = result;
        message.result = ResultCode.Companion.getMap().get(result);
        return message;
    }

    @RequestMapping("/get")
    public ObjectMessage getUser(@RequestParam(value = "userId", required = false, defaultValue = "-1") long userId,
                                 @RequestParam(value = "stuNo", required = false, defaultValue = "-1") String stuNo) {
        ObjectMessage message = new ObjectMessage();
        User user;
        if(userId!=-1) {
            user = userService.getUser(userId);
        } else {
            user = userService.getUserByStuNo(stuNo);
        }
        if (user == null) {
            message.code = ResultCode.Companion.getUSER_NOT_EXIST();
            message.result = ResultCode.Companion.getMap().get(message.code);
            return message;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        message.code = ResultCode.Companion.getSUCCESS();
        message.result = ResultCode.Companion.getMap().get(message.code);
        message.object = userDto;
        return message;

    }

    @RequestMapping("/login")
    public ObjectMessage userLogin(@RequestParam("stuNo") String stuNo,
                                   @RequestParam("passwd") String passwd) {
        ObjectMessage message = new ObjectMessage();
        Map<String, Object> result = userService.userLogin(stuNo, passwd);
        message.code = (int) result.get("code");
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        if(message.code == WyyResultCode.Companion.getSUCCESS()) {
            User user = (User) result.get("user");
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            message.object = userDto;
        }
        return message;
    }

    @RequestMapping("/addActivityApply")
    public BaseMessage addActivity(@RequestBody ActivityApply activityApply) {
        BaseMessage message = new BaseMessage();
        message.code = activityApplyService.addActivityApply(activityApply);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/signInApply")
    public BaseMessage signInApply(@RequestBody ActivityApply activityApply) {
        BaseMessage message = new BaseMessage();
        message.code = activityApplyService.signInActivityApply(activityApply.getActivityApplyId());
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }


}
