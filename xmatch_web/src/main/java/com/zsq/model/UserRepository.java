package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/4.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    public User findUserByXuehao(String xuehao);


}
