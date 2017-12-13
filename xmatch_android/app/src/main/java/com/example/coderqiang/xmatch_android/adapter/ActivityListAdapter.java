package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.ActivityApply;

import java.util.List;

/**
 * Created by coderqiang on 2017/12/13.
 */

public class ActivityListAdapter extends RecyclerView.Adapter {

    List<ActivityApply> activityApplies;
    Context context;

    public ActivityListAdapter(List<ActivityApply> activityApplies, Context context) {
        this.activityApplies = activityApplies;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActivityApplyHolder(LayoutInflater.from(context).inflate(R.layout.item_activity_apply,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (activityApplies==null) return 0;
        return activityApplies.size();
    }



    class ActivityApplyHolder extends RecyclerView.ViewHolder{

        public ActivityApplyHolder(View itemView) {
            super(itemView);
        }
    }
}
