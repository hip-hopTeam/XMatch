package com.example.coderqiang.xmatch_android.dto;

import com.example.coderqiang.xmatch_android.model.Department;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepartmentListMessage extends BaseMessage{
    public List<Department> object;

    public List<Department> getObject() {
        return object;
    }

    public void setObject(List<Department> object) {
        this.object = object;
    }
}
