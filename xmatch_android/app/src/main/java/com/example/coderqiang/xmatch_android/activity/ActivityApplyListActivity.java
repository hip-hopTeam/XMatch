package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.coderqiang.xmatch_android.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by coderqiang on 2017/12/13.
 */

public class ActivityApplyListActivity extends Activity {

    @BindView(R.id.activity_apply_back)
    ImageView back;
    @BindView(R.id.manager_activity_bar)
    AppBarLayout managerActivityBar;
    @BindView(R.id.activity_apply_recycler)
    RecyclerView activityApplyRecycler;
    @BindView(R.id.activity_apply_refresh)
    TwinklingRefreshLayout activityApplyRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_list);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.activity_apply_back)
    public void onViewClicked() {
        super.onBackPressed();
    }
}
