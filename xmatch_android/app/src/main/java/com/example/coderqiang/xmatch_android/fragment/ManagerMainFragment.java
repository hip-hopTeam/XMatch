package com.example.coderqiang.xmatch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.AddDepartmentActivity;
import com.example.coderqiang.xmatch_android.util.SwtichActivityUtil;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class ManagerMainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.manager_main_menu)
    ImageView menuBtn;
    @BindView(R.id.manager_add_dep_add)
    ImageView addBtn;


    @BindView(R.id.manager_main_avator)
    CircleImagview managerMainAvator;
    @BindView(R.id.manager_main_name)
    TextView managerMainName;
    @BindView(R.id.manager_main_setting_btn)
    ImageView managerMainSettingBtn;
    @BindView(R.id.manager_main_summary_tv)
    TextView managerMainSummaryTv;
    @BindView(R.id.manager_main_dep_num)
    TextView managerMainDepNum;
    @BindView(R.id.manager_main_activity_num)
    TextView managerMainActivityNum;
    @BindView(R.id.manager_main_apply_num)
    TextView managerMainApplyNum;
    @BindView(R.id.manager_main_bar)
    AppBarLayout managerMainBar;

    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_main, container, false);
        ButterKnife.bind(this, view);
        menuBtn = view.findViewById(R.id.manager_main_menu);
        addBtn = view.findViewById(R.id.manager_add_dep_add);
        initView();
        return view;
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        menuBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manager_main_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.manager_add_dep_add:
                SwtichActivityUtil.toActivity(getActivity(), AddDepartmentActivity.class);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
