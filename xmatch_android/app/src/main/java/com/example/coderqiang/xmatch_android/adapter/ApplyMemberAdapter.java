package com.example.coderqiang.xmatch_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.coderqiang.xmatch_android.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class ApplyMemberAdapter extends RecyclerView.Adapter{

    List<User> users;

    public ApplyMemberAdapter(List<User> users) {
        this.users = users;
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
        return users.size();
    }

    private class ApplyViewHolder extends RecyclerView.ViewHolder {

        public ApplyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
