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
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.fragment.MemberFragment;
import com.example.coderqiang.xmatch_android.model.DepMember;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class OfficeMemberAdapter extends RecyclerView.Adapter {
    private static final String TAG="OfficeMemberAdapter";

    private static final int TYPE_APPLY=1,TYPE_OFFICE=2,TYPE_MIDDLE=3;

    List<MemberDto> memberDtos;
    MemberFragment context;
    int middle=0;

    public OfficeMemberAdapter(List<MemberDto> memberDtos, MemberFragment memberFragment) {
        this.memberDtos = memberDtos;
        this.context = memberFragment;

        Log.i(TAG, "OfficeMemberAdapter: size:"+memberDtos.size());
        Collections.sort(memberDtos, new Comparator<MemberDto>() {
            @Override
            public int compare(MemberDto memberDto, MemberDto t1) {
                if (memberDto.getState() > t1.getState()) {
                    return 1;
                } else if (memberDto.getState() < t1.getState()) {
                    return -1;
                }
                return 0;
            }
        });

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
            return new MiddleViewHolder(LayoutInflater.from(context.getActivity()).inflate(R.layout.item_member_middle, parent, false));
        }else if (viewType == TYPE_APPLY) {
            return new ApplyViewHolder(LayoutInflater.from(context.getActivity()).inflate(R.layout.item_member_apply, parent, false));
        } else if (viewType==TYPE_OFFICE){
            return new OfficeViewHolder(LayoutInflater.from(context.getActivity()).inflate(R.layout.item_member_office, parent, false));
        }else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MIDDLE) {
            return;
        } else if (getItemViewType(position) ==TYPE_APPLY) {
            final MemberDto memberDto = memberDtos.get(position);
            ApplyViewHolder applyViewHolder = (ApplyViewHolder) holder;
            //职位:部长     手机:13110521828
            applyViewHolder.itemMemberApplyName.setText(memberDto.getUsername());
            applyViewHolder.role.setText("职位: "+memberDto.getRole());
            applyViewHolder.itemMemberApplyAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DepMember depMember = new DepMember();
                    depMember.setDepId(memberDto.getDepId());
                    depMember.setDepMemberId(memberDto.getDepMemberId());
                    depMember.setJoinTime(memberDto.getJoinTime());
                    depMember.setRole(memberDto.getRole());
                    depMember.setState(DepMember.STATE_OFFICE);
                    depMember.setUserId(memberDto.getUserId());
                    updateMember(depMember);
                }
            });
            applyViewHolder.itemMemberApplyDisagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DepMember depMember = new DepMember();
                    depMember.setDepId(memberDto.getDepId());
                    depMember.setDepMemberId(memberDto.getDepMemberId());
                    depMember.setJoinTime(memberDto.getJoinTime());
                    depMember.setRole(memberDto.getRole());
                    depMember.setState(DepMember.STATE_REFUSE);
                    depMember.setUserId(memberDto.getUserId());
                    updateMember(depMember);
                }
            });
        } else if (getItemViewType(position) == TYPE_OFFICE) {
            MemberDto memberDto = memberDtos.get(position -1);
            OfficeViewHolder officeViewHolder = (OfficeViewHolder) holder;
            officeViewHolder.itemMemberOfficeName.setText(memberDto.getUsername());
            officeViewHolder.role.setText("职位: "+memberDto.getRole());
        }
        Log.i(TAG, "onBindViewHolder: " + position);

    }

    private void updateMember(final DepMember member){
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result= DepManagerApi.updateMemberState(member, context.getActivity());
                subscriber.onNext(result);

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
                String result = (String) object;
                Toast.makeText(context.getActivity(), result, Toast.LENGTH_LONG).show();
                context.refreshData();
            }
        });
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
