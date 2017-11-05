package com.zsq.controller;

import com.zsq.model.User;
import com.zsq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by coderqiang on 2017/11/4.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addUser(User user) {
        userService.addUser(user);
        return "success";
    }
}
