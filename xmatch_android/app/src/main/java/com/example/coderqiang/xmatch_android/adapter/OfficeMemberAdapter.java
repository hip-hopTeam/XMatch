package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class OfficeMemberAdapter extends RecyclerView.Adapter {
    private static final String TAG="OfficeMemberAdapter";

    private static final int TYPE_APPLY=1,TYPE_OFFICE=2,TYPE_MIDDLE=3;

    List<MemberDto> memberDtos;
    Context context;
    int middle=0;

    public OfficeMemberAdapter(List<MemberDto> memberDtos, Context context) {
        this.memberDtos = memberDtos;
        this.context = context;
        for(int i=0;i<memberDtos.size();i++) {
            if (memberDtos.get(i).getState() == TYPE_OFFICE) {
                middle = i;
                break;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_MIDDLE) {
            return new MiddleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member_middle, parent, false));
        }else if (viewType == TYPE_APPLY) {
            return new ApplyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member_apply, parent, false));
        } else {
            return new OfficeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member_office, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MIDDLE) {
            return;
        } else if (getItemViewType(position) ==TYPE_APPLY) {
            MemberDto memberDto = memberDtos.get(position);
            ApplyViewHolder applyViewHolder = (ApplyViewHolder) holder;
            //职位:部长     手机:13110521828
            applyViewHolder.itemMemberApplyName.setText(memberDto.getUsername());
            applyViewHolder.role.setText("职位: "+memberDto.getRole());
        } else if (getItemViewType(position) == TYPE_OFFICE) {
            MemberDto memberDto = memberDtos.get(position -1);
            OfficeViewHolder officeViewHolder = (OfficeViewHolder) holder;
            officeViewHolder.itemMemberOfficeName.setText(memberDto.getUsername());
            officeViewHolder.role.setText("职位: "+memberDto.getRole());
        }
        Log.i(TAG, "onBindViewHolder: " + position);

    }

    @Override
    public int getItemCount() {
        return memberDtos.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < middle) {
            return TYPE_APPLY;
        } else if (position==middle) {
            return TYPE_MIDDLE;
        }else {
            return TYPE_OFFICE;
        }
    }

    class OfficeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_member_office_avator)
        CircleImagview itemMemberOfficeAvator;
        @BindView(R.id.item_member_office_name)
        TextView itemMemberOfficeName;
        @BindView(R.id.item_member_office_message)
        ImageView itemMemberOfficeMessage;
        @BindView(R.id.item_member_office_phone)
        ImageView itemMemberOfficePhone;
        @BindView(R.id.item_member_office_layout)
        LinearLayout layout;
        @BindView(R.id.item_member_office_role)
        TextView role;

        public OfficeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_member_apply_avator)
        CircleImagview itemMemberApplyAvator;
        @BindView(R.id.item_member_apply_name)
        TextView itemMemberApplyName;
        @BindView(R.id.item_member_apply_agree)
        ImageView itemMemberApplyAgree;
        @BindView(R.id.item_member_apply_disagree)
        ImageView itemMemberApplyDisagree;
        @BindView(R.id.item_member_apply_layout)
        LinearLayout layout;
        @BindView(R.id.item_member_apply_role)
        TextView role;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MiddleViewHolder extends RecyclerView.ViewHolder {

        public MiddleViewHolder(View itemView) {
            super(itemView);
        }
    }


}
