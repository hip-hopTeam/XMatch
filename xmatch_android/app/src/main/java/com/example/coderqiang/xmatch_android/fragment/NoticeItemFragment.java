package com.example.coderqiang.xmatch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.adapter.NoticeAdapter;
import com.example.coderqiang.xmatch_android.api.NoticeApi;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.AppNotice;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/24.
 */

public class NoticeItemFragment extends Fragment {
    private static final String TAG = "NoticeItemFragment";

    @BindView(R.id.notice_list_fragment_recycler)
    RecyclerView noticeListFragmentRecycler;
    @BindView(R.id.notice_list_fragment_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    Unbinder unbinder;

    //1 全校 2 部门
    int type=1;

    public static NoticeItemFragment getInstance(int type) {
        NoticeItemFragment noticeItemFragment = new NoticeItemFragment();
        noticeItemFragment.type=type;
        return noticeItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData(false);
        return view;
    }

    private void initView() {
        noticeListFragmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(true);
            }
        });
    }

    private void initData(boolean refresh) {
        System.out.println("获取数据");
        Observable.create(subscriber -> {
                List<AppNotice> notices = NoticeApi.getNotices(type, DepManagerLab.get(getActivity()).getDepManagerDto().getDepartmentId());
                subscriber.onNext(notices);
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
                List<AppNotice> appNotices=(List<AppNotice>)object;
                if (appNotices != null) {
                    noticeListFragmentRecycler.setAdapter(new NoticeAdapter(appNotices,NoticeItemFragment.this));
                    if (refresh)
                        Toast.makeText(getActivity(), "更新数据成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "无数据", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
