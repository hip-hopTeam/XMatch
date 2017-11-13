package com.example.coderqiang.xmatch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.OfficeMemberAdapter;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class MemberFragment extends Fragment {
    private static final String TAG = "MemberFragment ";

    @BindView(R.id.manager_member_menu)
    ImageView managerMemberMenu;
    @BindView(R.id.manager_member_search)
    SearchView managerMemberSearch;
    @BindView(R.id.manager_main_bar)
    AppBarLayout managerMainBar;
    @BindView(R.id.manager_member_office_recycler)
    RecyclerView managerMemberOfficeRecycler;

    Unbinder unbinder;

    List<MemberDto> memberDtos = new ArrayList<>();

    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<List<MemberDto>>() {
            @Override
            public void call(Subscriber<? super List<MemberDto>> subscriber) {
                List<MemberDto> memberDtos=DepManagerApi.getMembersByState(getActivity());
                if (memberDtos == null) {
                    subscriber.onCompleted();
                }else {
                    subscriber.onNext(memberDtos);
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MemberDto>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: memeberDto==null");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<MemberDto> memberDtos) {
                List<MemberDto> dtos = new ArrayList<>();
                for (MemberDto memberDto : memberDtos) {
                    if (memberDto.getState()==MemberDto.STATE_APPLY||memberDto.getState()==MemberDto.STATE_OFFICE){
                        dtos.add(memberDto);
                    }
                }
                MemberFragment.this.memberDtos = dtos;
                managerMemberOfficeRecycler.setAdapter(new OfficeMemberAdapter(dtos,MemberFragment.this));
            }
        });
    }

    public void refreshData() {
        System.out.println("refresh");
        Observable.create(new Observable.OnSubscribe<List<MemberDto>>() {
            @Override
            public void call(Subscriber<? super List<MemberDto>> subscriber) {
                List<MemberDto> memberDtos=DepManagerApi.getMembersByState(getActivity());
                if (memberDtos == null) {
                    subscriber.onCompleted();
                }else {
                    subscriber.onNext(memberDtos);
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MemberDto>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: memeberDto==null");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<MemberDto> memberDtos) {
                MemberFragment.this.memberDtos=memberDtos;
                managerMemberOfficeRecycler.setAdapter(new OfficeMemberAdapter(memberDtos,MemberFragment.this));
            }
        });
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        managerMemberOfficeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.manager_member_menu, R.id.manager_member_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_member_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.manager_member_search:
                break;
        }
    }
}
