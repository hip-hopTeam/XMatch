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
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.DepartmentAdapter;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepartmentFragment extends Fragment implements View.OnClickListener {
    private static final String TAG="DepartmentFragment";

    @BindView(R.id.manager_member_menu)
    ImageView managerMemberMenu;
    @BindView(R.id.manager_member_search)
    SearchView managerMemberSearch;
    @BindView(R.id.manager_main_bar)
    AppBarLayout managerMainBar;
    @BindView(R.id.manager_department_recycler)
    RecyclerView managerDepartmentRecycler;
    Unbinder unbinder;

    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        managerDepartmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        managerMemberMenu.setOnClickListener(this);
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
               List<Department> departments = DepManagerApi.getAllDepartments();
                subscriber.onNext(departments);
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
                List<Department> departments=(List<Department>)object;
                managerDepartmentRecycler.setAdapter(new DepartmentAdapter(departments,DepartmentFragment.this));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.manager_member_menu, R.id.manager_main_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_member_menu:
                break;
            case R.id.manager_main_bar:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manager_member_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;

        }
    }
}
