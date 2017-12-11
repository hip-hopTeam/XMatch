package com.example.coderqiang.xmatch_android.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.ActivityActivity;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.fragment.DepartmentFragment;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.Department;
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
        final Activity activity = activities.get(position);
        activityHolder.itemManagerActivityTitle.setText(activity.getActivityName()+"");
        activityHolder.itemManagerActivityAddress.setText(activity.getAddress()+"");
        activityHolder.itemManagerActivityContent.setText(activity.getContent() + "");
        activityHolder.itemManagerActivityDate.setText(new SimpleDateFormat("MM.dd HH:mm").format(activity.getStartTime()));
        int res=0;
        if (activity.getEndTime() < System.currentTimeMillis()) {
            res = R.drawable.activity_stop_circle;
        } else if (activity.getStartTime() < System.currentTimeMillis()) {
            res = R.drawable.activity_runing_circle;
        }else {
            res = R.drawable.activity_prepare_circle;
        }
        activityHolder.itemManagerActivityStatus.setImageDrawable(activityFragment.getResources().getDrawable(res));
        Glide.with(activityFragment).load(DefaultConfig.BASE_URL+activity.getImageUrl())
                .asBitmap().error(R.drawable.avator)
                .into(activityHolder.itemManagerActivityImage);

        activityHolder.itemManagerActivityLayout.setOnClickListener(view -> {
                Intent intent = new Intent(activityFragment.getActivity(), ActivityActivity.class);
                intent.putExtra(ActivityActivity.INTENT_ACTIVITY_ID, activity.getActivityId());
                activityFragment.getActivity().startActivity(intent);
        });

        if (activity.getDepId() != DepManagerLab.get(activityFragment.getActivity()).getDepManagerDto().getDepartmentId()) {
            activityHolder.itemManagerActivityDelete.setVisibility(View.GONE);
        }else {
            activityHolder.itemManagerActivityDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(Subscriber<? super Object> subscriber) {
                            int result= ActivityApi.deleteActivity(activityFragment.getActivity(), activity.getActivityId());
                            subscriber.onNext(result);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(Object object) {
                            int result=(int)object;
                            if (result == ResultCode.Companion.getSUCCESS()) {
                                Toast.makeText(activityFragment.getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                activityFragment.initData(false);
                                DepManagerLab.get(activityFragment.getContext()).getDepManagerDto().setActivityNum(DepManagerLab.get(activityFragment.getContext()).getDepManagerDto().getActivityNum()-1);
                            }else {
                                Toast.makeText(activityFragment.getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
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
        @BindView(R.id.item_manager_activity_status)
        ImageView itemManagerActivityStatus;

        public ActivityHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
