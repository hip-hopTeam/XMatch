package com.example.coderqiang.xmatch_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.coderqiang.xmatch_android.model.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepartmentAdapter extends RecyclerView.Adapter {

    List<Department> departments = new ArrayList<>();

    public DepartmentAdapter(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    private class Department
}
