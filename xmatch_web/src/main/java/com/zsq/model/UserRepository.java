package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    /**
     * 返回对应学号的学生
     * @param stuNo 学号
     * @return 返回对应学号的学生
     */
    public User findUserByStuNo(String stuNo);


}
