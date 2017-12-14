package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class UserMessage extends BaseMessage {
    private UserDto object;

    public UserDto getObject() {
        return object;
    }

    public void setObject(UserDto object) {
        this.object = object;
    }
}
