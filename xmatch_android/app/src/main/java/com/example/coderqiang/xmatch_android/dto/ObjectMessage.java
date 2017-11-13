package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class ObjectMessage<T> extends BaseMessage{
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
