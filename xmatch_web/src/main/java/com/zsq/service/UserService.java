package com.zsq.service;

import com.zsq.model.User;

import java.util.Map;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 */

public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    public User getUser(long userId);

    public User getUserByStuNo(String stuNo);

    /**
     * 登陆
     */
    public Map<String, Object> userLogin(String stuNo, String passwd);
}
