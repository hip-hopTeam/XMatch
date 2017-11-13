package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class ApplyMemberAdapter extends RecyclerView.Adapter{

    List<User> users;
    Context context;

    public ApplyMemberAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member_apply,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        ApplyViewHolder applyViewHolder = (ApplyViewHolder) holder;
        applyViewHolder.itemMemberApplyName.setText(user.getUsername());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_member_apply_avator)
        CircleImagview itemMemberApplyAvator;
        @BindView(R.id.item_member_apply_name)
        TextView itemMemberApplyName;
        @BindView(R.id.item_member_apply_agree)
        ImageView itemMemberApplyAgree;
        @BindView(R.id.item_member_apply_disagree)
        ImageView itemMemberApplyDisagree;
        @BindView(R.id.item_member_apply_layout)
        LinearLayout layout;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
