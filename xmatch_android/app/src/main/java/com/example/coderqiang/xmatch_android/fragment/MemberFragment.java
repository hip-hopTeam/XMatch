package com.example.coderqiang.xmatch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.OfficeMemberAdapter;
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
        for (int i = 0; i < 4; i++) {
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail("976928202@qq.com");
            memberDto.setPhoneNum("13110521828");
            memberDto.setUsername("郑世强");
            memberDto.setRole("普通成员");
            memberDto.setState(MemberDto.STATE_APPLY);
            memberDtos.add(memberDto);
        }
        for (int i = 0; i < 8; i++) {
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail("976928202@qq.com");
            memberDto.setPhoneNum("13110521828");
            memberDto.setUsername("叶岗村");
            memberDto.setRole("普通成员");
            memberDto.setState(MemberDto.STATE_OFFICE);
            memberDtos.add(memberDto);
        }
        //按状态排序
        Collections.sort(memberDtos, new Comparator<MemberDto>() {
            @Override
            public int compare(MemberDto memberDto, MemberDto t1) {
                if (memberDto.getState() > t1.getState()) {
                    return 1;
                } else if (memberDto.getState()<t1.getState()){
                    return -1;
                }
                return 0;
            }
        });
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        managerMemberOfficeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        managerMemberOfficeRecycler.setAdapter(new OfficeMemberAdapter(memberDtos,getActivity()));
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
