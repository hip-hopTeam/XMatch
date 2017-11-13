package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ChildDepartmentAdapter extends RecyclerView.Adapter {

    List<ChildDepartment> childDepartments;
    Context context;

    public ChildDepartmentAdapter(List<ChildDepartment> childDepartments, Context context) {
        this.childDepartments = childDepartments;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildDepartmentHolder(LayoutInflater.from(context).inflate(R.layout.item_child_department, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChildDepartmentHolder childDepartmentHolder = (ChildDepartmentHolder) holder;
        ChildDepartment childDepartment = childDepartments.get(position);
        childDepartmentHolder.itemChildDepartmentEmail.setText("邮箱:    "+childDepartment.getEmail());
        childDepartmentHolder.itemChildDepartmentName.setText(childDepartment.getName());
    }

    @Override
    public int getItemCount() {
        return childDepartments.size();
    }

    class ChildDepartmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_child_department_name)
        TextView itemChildDepartmentName;
        @BindView(R.id.item_child_department_email)
        TextView itemChildDepartmentEmail;
        @BindView(R.id.item_child_department_message)
        ImageView itemChildDepartmentMessage;
        @BindView(R.id.item_child_department_phone)
        ImageView itemChildDepartmentPhone;
        public ChildDepartmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
