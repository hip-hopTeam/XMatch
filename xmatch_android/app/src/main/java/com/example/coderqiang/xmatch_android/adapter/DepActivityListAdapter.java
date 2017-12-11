package com.example.coderqiang.xmatch_android.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.ActivityActivity;
import com.example.coderqiang.xmatch_android.activity.ActivityListActivity;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/24.
 */

public class DepActivityListAdapter extends RecyclerView.Adapter {
    List<Activity> activities;
    ActivityListActivity activityContext;



    public DepActivityListAdapter(List<Activity> activities, ActivityListActivity activityContext) {
        this.activities = activities;
        this.activityContext = activityContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActivityListHolder(LayoutInflater.from(activityContext).inflate(R.layout.item_manager_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ActivityListHolder activityHolder = (ActivityListHolder) holder;
        final Activity activity = activities.get(position);
        activityHolder.itemManagerActivityTitle.setText(activity.getActivityName()+"");
        activityHolder.itemManagerActivityAddress.setText(activity.getAddress()+"");
        activityHolder.itemManagerActivityContent.setText(activity.getContent() + "");
        activityHolder.itemManagerActivityDate.setText(new SimpleDateFormat("MM.dd HH:mm").format(activity.getStartTime()));
        int res=0;
        if (System.currentTimeMillis() > activity.getEndTime()) {
            res = R.drawable.activity_stop_circle;
        } else if (activity.getStartTime() < System.currentTimeMillis()) {
            res = R.drawable.activity_runing_circle;
        }else {
            res = R.drawable.activity_prepare_circle;
        }
        activityHolder.itemManagerActivityStatus.setImageDrawable(activityContext.getResources().getDrawable(res));
        Glide.with(activityContext).load(DefaultConfig.BASE_URL+activity.getImageUrl())
                .asBitmap().error(R.drawable.avator)
                .into(activityHolder.itemManagerActivityImage);

        activityHolder.itemManagerActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activityContext, ActivityActivity.class);
                intent.putExtra(ActivityActivity.INTENT_ACTIVITY_ID, activity.getActivityId());
                activityContext.startActivity(intent);
            }
        });

        if (activity.getDepId() != DepManagerLab.get(activityContext).getDepManagerDto().getDepartmentId()) {
            activityHolder.itemManagerActivityDelete.setVisibility(View.GONE);
        }else {
            activityHolder.itemManagerActivityDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(Subscriber<? super Object> subscriber) {
                            int result= ActivityApi.deleteActivity(activityContext, activity.getActivityId());
                            subscriber.onNext(result);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Toast.makeText(activityContext, "网络错误", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(Object object) {
                            int result=(int)object;
                            if (result == ResultCode.Companion.getSUCCESS()) {
                                Toast.makeText(activityContext, "删除成功", Toast.LENGTH_SHORT).show();
                                activityContext.initData();
                                DepManagerLab.get(activityContext).getDepManagerDto().setActivityNum(DepManagerLab.get(activityContext).getDepManagerDto().getActivityNum()-1);
                            }else {
                                Toast.makeText(activityContext, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    class ActivityListHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.item_manager_activity_status)
        ImageView itemManagerActivityStatus;

        public ActivityListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
