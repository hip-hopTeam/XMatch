package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.adapter.DepActivityListAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/15.
 */

public class ActivityListActivity extends Activity {
    private static final String TAG="ActivityListActivity";


    @BindView(R.id.dep_list_back)
    ImageView depListBack;
    @BindView(R.id.dep_list_activity_bar)
    AppBarLayout depListActivityBar;
    @BindView(R.id.dep_list_recycler)
    RecyclerView depListRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_list_activity);
        ButterKnife.bind(this);
        initData();
        initView();
        WindowUtil.setConfig(this);
    }

    private void initView() {
        depListRecycler.setLayoutManager(new LinearLayoutManager(this));
        depListBack.setOnClickListener(v -> super.onBackPressed());
    }

    public void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<com.example.coderqiang.xmatch_android.model.Activity> activities = ActivityApi.getDepActivity(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepartmentId());
                subscriber.onNext(activities);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: memeberDto==null");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Object object) {
                List<com.example.coderqiang.xmatch_android.model.Activity> activities=(List<com.example.coderqiang.xmatch_android.model.Activity>)object;
                System.out.println("size:"+activities.size());
                DepActivityListAdapter activityAdapter=new DepActivityListAdapter(activities,ActivityListActivity.this);
                depListRecycler.setAdapter(activityAdapter);
            }
        });
    }

}
