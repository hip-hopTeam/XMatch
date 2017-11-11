package com.zsq.service.impl;

import com.zsq.model.User;
import com.zsq.model.UserRepository;
import com.zsq.service.UserService;
import com.zsq.util.ResultCode;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 */
@Service
@EnableTransactionManagement
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;


    @Override
    public int addUser(User user) {
        User isExistUser=repository.findUserByStuNo(user.getStuNo());
        if (isExistUser != null && isExistUser.getUserId() >= 0) {
            return ResultCode.Companion.getUSER_EXIST();
        }
        repository.save(user);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateUser(User user) {
        User resUser = repository.findOne(user.getUserId());
        if(resUser==null) {
            return WyyResultCode.Companion.getUSER_NOT_EXIST();
        }
        String stuNo = resUser.getStuNo();
        String passwd = resUser.getPasswd();
        BeanUtils.copyProperties(user, resUser);
        resUser.setStuNo(stuNo);
        resUser.setPasswd(passwd);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    public User getUser(long userId) {
        User user = repository.findOne(userId);
        return user;
    }

    @Override
    public User getUserByStuNo(String stuNo) {
        User user = repository.findUserByStuNo(stuNo);
        return user;
    }

    @Override
    public Map<String, Object> userLogin(String stuNo, String passwd) {
        Map<String, Object> result=new HashMap<>();
        User user = repository.findUserByStuNo(stuNo);
        if(user == null) {
            result.put("code",WyyResultCode.Companion.getUSER_NOT_EXIST());
            return result;
        }
        if(!passwd.equals(user.getPasswd())) {
            result.put("code",WyyResultCode.Companion.getUSER_PASSWORD_ERROR());
            return result;
        }
        result.put("code",WyyResultCode.Companion.getSUCCESS());
        result.put("user",user);
        return result;
    }


}
