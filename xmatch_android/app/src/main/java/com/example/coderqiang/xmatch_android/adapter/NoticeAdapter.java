package com.example.coderqiang.xmatch_android.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.NoticeActivity;
import com.example.coderqiang.xmatch_android.model.AppNotice;
import com.example.coderqiang.xmatch_android.view.CircleImagview;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/24.
 */

public class NoticeAdapter extends RecyclerView.Adapter {

    List<AppNotice> appNotices = new ArrayList<>();
    Fragment fragment;

    public NoticeAdapter(List<AppNotice> appNotices, Fragment fragment) {
        this.appNotices = appNotices;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeHolder(LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_notice, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoticeHolder noticeHolder = (NoticeHolder) holder;
        AppNotice appNotice = appNotices.get(position);
        noticeHolder.itemNoticeTitle.setText(appNotice.getTitle());
        noticeHolder.itemNoticeContent.setText(appNotice.getContent());
        noticeHolder.itemNoticeDate.setText(new SimpleDateFormat("MM:dd").format(appNotice.getCreateTime()));
        noticeHolder.itemNoticeDep.setText(appNotice.getDeparmentName());
        if (appNotice.getDeparmentName() != null && appNotice.getDeparmentName().length() >= 1) {
            noticeHolder.itemNoticeAvator.setText(appNotice.getDeparmentName().substring(0,1));
        }
        noticeHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragment.getActivity(), NoticeActivity.class);
                intent.putExtra(NoticeActivity.EXTRA_NOTICE, new Gson().toJson(appNotice));
                fragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appNotices.size();
    }

    class NoticeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notice_layout)
        LinearLayout layout;
        @BindView(R.id.item_notice_avator)
        TextView itemNoticeAvator;
        @BindView(R.id.item_notice_title)
        TextView itemNoticeTitle;
        @BindView(R.id.item_notice_dep)
        TextView itemNoticeDep;
        @BindView(R.id.item_notice_date)
        TextView itemNoticeDate;
        @BindView(R.id.item_notice_content)
        TextView itemNoticeContent;

        public NoticeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
