package com.zsq.service.impl;

import com.zsq.model.User;
import com.zsq.model.UserRepository;
import com.zsq.service.UserService;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by coderqiang on 2017/11/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public int addUser(User user) {
        repository.save(user);
        return ResultCode.Companion.getSUCCESS();
    }
}
