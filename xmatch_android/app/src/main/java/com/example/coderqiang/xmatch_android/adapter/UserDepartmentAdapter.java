package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class UserDepartmentAdapter extends RecyclerView.Adapter<UserDepartmentAdapter.UserDepHolder> {
    List<Department> departments;
    Context context;

    public UserDepartmentAdapter(Context context,List<Department> departments) {
        this.departments = departments;
        this.context = context;
    }

    @Override
    public UserDepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDepHolder(LayoutInflater.from(context).inflate(R.layout.item_user_dep, parent, false));
    }

    @Override
    public void onBindViewHolder(UserDepHolder holder, int position) {
        Department department = departments.get(position);
        holder.itemUserDepName.setText(department.getDepName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Glide.with(context).load(DefaultConfig.BASE_URL+department.getImageUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).error(R.drawable.avator)
                .into(holder.itemUserDepAvator);

    }

    @Override
    public int getItemCount() {
        return departments.size();
    }


    class UserDepHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_user_dep_layout)
        LinearLayout layout;
        @BindView(R.id.item_user_dep_avator)
        CircleImagview itemUserDepAvator;
        @BindView(R.id.item_user_dep_name)
        TextView itemUserDepName;
        @BindView(R.id.item_user_dep_activity)
        TextView itemUserDepActivity;
        @BindView(R.id.item_department_more)
        ImageView itemDepartmentMore;


        public UserDepHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
