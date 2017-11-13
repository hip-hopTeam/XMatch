package com.example.coderqiang.xmatch_android.dto;

import com.example.coderqiang.xmatch_android.model.ChildDepartment;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ChildDepartmentListMessage extends BaseMessage {
    private List<ChildDepartment> object;

    public List<ChildDepartment> getObject() {
        return object;
    }

    public void setObject(List<ChildDepartment> object) {
        this.object = object;
    }
}
