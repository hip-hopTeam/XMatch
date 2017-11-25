package com.example.coderqiang.xmatch_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.AddNoticeActivity;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.NoticeApi;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.AppNotice;

import java.util.ArrayList;
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
 * Created by coderqiang on 2017/11/14.
 */

public class NoticeFragment extends Fragment {

    @BindView(R.id.manager_notice_menu)
    ImageView managerNoticeMenu;
    @BindView(R.id.manager_notice_tab_layout)
    TabLayout managerNoticeTabLayout;
    @BindView(R.id.manager_notice_add)
    ImageView managerNoticeAdd;
    @BindView(R.id.manager_notice_bar)
    AppBarLayout managerNoticeBar;
    @BindView(R.id.manager_notice_viewPager)
    ViewPager managerNoticeViewPager;
    Unbinder unbinder;

    DrawerLayout drawer;
    PagerAdapter mAdapter;
    List<Fragment> fragments;
    FragAdapter fragAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        initView();
        return rootView;
    }

    private void initView() {
        drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        managerNoticeMenu.setOnClickListener(v->drawer.openDrawer(Gravity.LEFT));
        fragments = new ArrayList<>();
        fragments.add(NoticeItemFragment.getInstance(2));
        fragments.add(NoticeItemFragment.getInstance(1));
        fragAdapter = new FragAdapter(getFragmentManager(),fragments);
        managerNoticeViewPager.setAdapter(fragAdapter);
        managerNoticeViewPager.setCurrentItem(0);
        managerNoticeTabLayout.setTabsFromPagerAdapter(fragAdapter);
        managerNoticeTabLayout.setupWithViewPager(managerNoticeViewPager);
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        fragments.clear();
        fragments.add(NoticeItemFragment.getInstance(2));
        fragments.add(NoticeItemFragment.getInstance(1));
        fragAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.manager_notice_menu, R.id.manager_notice_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_notice_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.manager_notice_add:
                Intent intent = new Intent(getActivity(), AddNoticeActivity.class);
                startActivity(intent);
                break;
        }
    }

    class FragAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragments;

        public FragAdapter(FragmentManager fm,List<android.support.v4.app.Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "全校通知";
            }else {
                return "部门通知";
            }
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
