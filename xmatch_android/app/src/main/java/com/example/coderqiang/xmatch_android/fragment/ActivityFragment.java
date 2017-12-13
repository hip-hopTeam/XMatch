package com.example.coderqiang.xmatch_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.AddActivityActivity;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.adapter.DepartmentAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.Department;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ActivityFragment extends Fragment {

    private static final String TAG="ActivityFragment";

    @BindView(R.id.manager_activity_menu)
    ImageView managerActivityMenu;
    @BindView(R.id.manager_activity_add)
    ImageView managerActivityAdd;
    @BindView(R.id.manager_activity_recycler)
    RecyclerView managerActivityRecycler;
    @BindView(R.id.manager_activity_bar)
    AppBarLayout managerActivityBar;
    @BindView(R.id.manager_activity_refresh)
    TwinklingRefreshLayout refreshLayout;
    Unbinder unbinder;

    ActivityAdapter activityAdapter;

    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void initData(boolean refresh) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<Activity> activities = ActivityApi.getAllActivity(getActivity());
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
                List<Activity> activities=(List<Activity>)object;
                if (activities != null) {
                    if (refresh)
                    Toast.makeText(getActivity(), "刷新数据成功", Toast.LENGTH_SHORT).show();
                    activityAdapter=new ActivityAdapter(activities,ActivityFragment.this);
                    managerActivityRecycler.setAdapter(activityAdapter);
                }
                refreshLayout.finishRefreshing();
            }
        });
    }

    private void initView() {
        refreshLayout.setHeaderView(new SinaRefreshView(this.getActivity()));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                initData(true);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
            }
        });
        drawer = getActivity().findViewById(R.id.drawer_layout);
        managerActivityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddActivityActivity.class);
                startActivity(intent);
            }
        });
        managerActivityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        managerActivityMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
