package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class ObjectMessage<T> {
    private int code;
    private String result;
    private T object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
