package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by coderqiang on 2017/11/8.
 */
@Entity
public class EmailNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emailNoticeId;

    private String title;
    private String content;
    private long createTime;
    //要发送的email地址 json格式[{"userId":"2","emailUrl":"976928202@qq.com"},...]
    private String toEmailUrl;
}
