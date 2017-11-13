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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class OfficeMemberAdapter extends RecyclerView.Adapter {

    List<User> users;
    Context context;

    public OfficeMemberAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfficeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member_office, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        OfficeViewHolder officeViewHolder=(OfficeViewHolder)holder;
        officeViewHolder.itemMemberOfficeName.setText(user.getUsername());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class OfficeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_member_office_avator)
        CircleImagview itemMemberOfficeAvator;
        @BindView(R.id.item_member_office_name)
        TextView itemMemberOfficeName;
        @BindView(R.id.item_member_office_message)
        ImageView itemMemberOfficeMessage;
        @BindView(R.id.item_member_office_phone)
        ImageView itemMemberOfficePhone;
        @BindView(R.id.item_member_office_layout)
        LinearLayout layout;

        public OfficeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
