package com.example.coderqiang.xmatch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class MemberFragment extends Fragment {

    @BindView(R.id.manager_member_menu)
    ImageView managerMemberMenu;
    @BindView(R.id.manager_member_search)
    SearchView managerMemberSearch;
    @BindView(R.id.manager_main_bar)
    AppBarLayout managerMainBar;
    @BindView(R.id.manager_member_office_recycler)
    RecyclerView managerMemberOfficeRecycler;
    @BindView(R.id.manager_member_line)
    RelativeLayout managerMemberLine;
    @BindView(R.id.manager_member_apply_recycler)
    RecyclerView managerMemberApplyRecycler;

    Unbinder unbinder;

    List<User> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView() {

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
                break;
            case R.id.manager_member_search:
                break;
        }
    }
}
