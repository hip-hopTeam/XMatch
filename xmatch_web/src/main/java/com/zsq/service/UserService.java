package com.zsq.service;

import com.zsq.model.*;
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
    public Map<String,Object> addUser(User user);

    public int addUserAvator(long userId,String url);

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
