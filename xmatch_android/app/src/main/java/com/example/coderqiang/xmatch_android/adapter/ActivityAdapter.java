package com.example.coderqiang.xmatch_android.adapter;

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
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ActivityAdapter extends RecyclerView.Adapter {

    List<Activity> activities;
    ActivityFragment activityFragment;


    public ActivityAdapter(List<Activity> activities, ActivityFragment activityFragment) {
        this.activities = activities;
        this.activityFragment = activityFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActivityHolder(LayoutInflater.from(activityFragment.getActivity()).inflate(R.layout.item_manager_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ActivityHolder activityHolder = (ActivityHolder) holder;
        Activity activity = activities.get(position);

        activityHolder.itemManagerActivityTitle.setText(activity.getActivityName()+"");
        activityHolder.itemManagerActivityAddress.setText(activity.getAddress()+"");
        activityHolder.itemManagerActivityContent.setText(activity.getContent() + "");
        activityHolder.itemManagerActivityDate.setText(new SimpleDateFormat("MM.dd\nHH:mm").format(activity.getStartTime()));
        Glide.with(activityFragment).load(DefaultConfig.BASE_URL+activity.getImageUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).error(R.drawable.avator)
                .into(activityHolder.itemManagerActivityImage);

        activityHolder.itemManagerActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        activityHolder.itemManagerActivityDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    class ActivityHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_manager_activity_image)
        ImageView itemManagerActivityImage;
        @BindView(R.id.item_manager_activity_title)
        TextView itemManagerActivityTitle;
        @BindView(R.id.item_manager_activity_content)
        TextView itemManagerActivityContent;
        @BindView(R.id.item_manager_activity_date)
        TextView itemManagerActivityDate;
        @BindView(R.id.item_manager_activity_address)
        TextView itemManagerActivityAddress;
        @BindView(R.id.item_manager_activity_delete)
        TextView itemManagerActivityDelete;
        @BindView(R.id.item_manager_activity_layout)
        LinearLayout itemManagerActivityLayout;

        public ActivityHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
