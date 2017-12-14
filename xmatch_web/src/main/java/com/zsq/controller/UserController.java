package com.zsq.controller;

import com.zsq.dto.UserDto;
import com.zsq.model.ActivityApply;
import com.zsq.model.User;
import com.zsq.service.ActivityApplyService;
import com.zsq.service.DepartmentService;
import com.zsq.service.UserService;
import com.zsq.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/add")
    public ObjectMessage addUser(@RequestBody  User user) {
        ObjectMessage message = new ObjectMessage();
        Map<String,Object> resultMap=userService.addUser(user);
        message.code= (int) resultMap.get("code");
        message.result = ResultCode.Companion.getMap().get(message.code);
        message.object = resultMap.get("userId");
        return message;
    }

    @RequestMapping("/avator/add")
    public BaseMessage addDepManagerAvator(
            @RequestParam("userId") long userId
            , @RequestParam("avator")MultipartFile file,
            HttpServletRequest request) {
        BaseMessage message = new BaseMessage();

        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String prefix=file.getOriginalFilename().replaceAll(".*(\\.(.*))","$1");
        String url="/avator_user/"+userId+prefix;
        File avator = new File(rootPath+"/static"+url);
        if (!avator.getParentFile().exists()) {
            avator.getParentFile().mkdirs();
        }
        try {
            file.transferTo(avator);
        } catch (IOException e) {
            message.code = LsyResultCode.Companion.getERROR();
            message.result= LsyResultCode.Companion.getMap().get(message.code);
            e.printStackTrace();
            return message;
        }
        message.code = userService.addUserAvator(userId, url);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
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

    @RequestMapping("/getDeps")
    public ObjectMessage getDeps(
            @RequestParam long userId
    ){
        ObjectMessage message = new ObjectMessage();
        message.code=ResultCode.Companion.getSUCCESS();
        message.result = ResultCode.Companion.getMap().get(message.code);
        message.object = departmentService.getUserDepartment(userId);
        return message;
    }

}
