package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.ChildDepartmentActivity;
import com.example.coderqiang.xmatch_android.fragment.DepartmentFragment;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.SwtichActivityUtil;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepartmentAdapter extends RecyclerView.Adapter {

    List<Department> departments = new ArrayList<>();
    DepartmentFragment context;

    public DepartmentAdapter(List<Department> departments, DepartmentFragment context) {
        this.departments = departments;

        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DepartmentHolder(LayoutInflater.from(context.getActivity()).inflate(R.layout.item_department, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DepartmentHolder departmentHolder = (DepartmentHolder) holder;
        final Department department = departments.get(position);
        departmentHolder.itemDepartmentName.setText("" + department.getDepName());
        departmentHolder.itemDepartmentMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getActivity(), ChildDepartmentActivity.class);
                intent.putExtra("departmentId", department.getDepartmentId());
                intent.putExtra("departmentName", department.getDepName());
                context.getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class DepartmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_department_avator)
        CircleImagview itemDepartmentAvator;
        @BindView(R.id.item_department_name)
        TextView itemDepartmentName;
        @BindView(R.id.item_department_message)
        ImageView itemDepartmentMessage;
        @BindView(R.id.item_department_phone)
        ImageView itemDepartmentPhone;
        @BindView(R.id.item_department_more)
        ImageView itemDepartmentMore;

        public DepartmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
