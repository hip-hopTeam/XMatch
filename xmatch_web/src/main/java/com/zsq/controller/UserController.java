package com.zsq.controller;

import com.zsq.dto.UserDto;
import com.zsq.model.User;
import com.zsq.service.UserService;
import com.zsq.util.BaseMessage;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ObjectMessage getUser(@RequestParam("userId") long userId) {
        ObjectMessage message = new ObjectMessage();
        User user = userService.getUser(userId);
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
        message.result = WyyResultCode.Companion.getMap().get(result);
        if(message.code == WyyResultCode.Companion.getSUCCESS()) {
            User user = (User) result.get("user");
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            message.object = userDto;
        }
        return message;
    }
}
