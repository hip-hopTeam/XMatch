package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.model.AppNotice;
import com.example.coderqiang.xmatch_android.util.WindowUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by coderqiang on 2017/11/24.
 */

public class NoticeActivity extends Activity {

    public static final String EXTRA_NOTICE="notice";

    @BindView(R.id.activity_detail_back)
    ImageView activityDetailBack;
    @BindView(R.id.item_notice_detaile_date)
    TextView itemNoticeDetaileDate;
    @BindView(R.id.manager_add_dep_bar)
    AppBarLayout managerAddDepBar;
    @BindView(R.id.item_notice_detaile_avator)
    TextView itemNoticeDetaileAvator;
    @BindView(R.id.item_notice_detail_title)
    TextView itemNoticeDetailTitle;
    @BindView(R.id.item_notice_detail_content)
    TextView itemNoticeDetailContent;

    AppNotice appNotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        WindowUtil.setConfig(this);
        String noticeStr = getIntent().getStringExtra(EXTRA_NOTICE);
        appNotice = new Gson().fromJson(noticeStr, AppNotice.class);
        initView();
    }

    private void initView() {
        itemNoticeDetailTitle.setText(appNotice.getTitle());
        itemNoticeDetailContent.setText(appNotice.getContent());
        itemNoticeDetaileAvator.setText(appNotice.getDeparmentName().substring(0,1));
        itemNoticeDetaileDate.setText(new SimpleDateFormat("yyyy.MM.dd HH.mm").format(appNotice.getCreateTime()));
    }

    @OnClick(R.id.activity_detail_back)
    public void onViewClicked() {
        super.onBackPressed();
    }
}
