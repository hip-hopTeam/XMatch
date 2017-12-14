package com.example.coderqiang.xmatch_android.adapter;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.coderqiang.xmatch_android.activity.ChildDepartmentActivity;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.PhoneUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ChildDepartmentAdapter extends RecyclerView.Adapter {

    List<ChildDepartment> childDepartments;
    ChildDepartmentActivity context;

    public ChildDepartmentAdapter(List<ChildDepartment> childDepartments, ChildDepartmentActivity context) {
        this.childDepartments = childDepartments;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildDepartmentHolder(LayoutInflater.from(context).inflate(R.layout.item_child_department, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChildDepartmentHolder childDepartmentHolder = (ChildDepartmentHolder) holder;
        final ChildDepartment childDepartment = childDepartments.get(position);
        childDepartmentHolder.itemChildDepartmentEmail.setText("邮箱:    " + childDepartment.getEmail());
        childDepartmentHolder.itemChildDepartmentName.setText(childDepartment.getName());
        childDepartmentHolder.itemChildDepartmentPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtil.call(context,childDepartment.getPhone());
            }
        });
        childDepartmentHolder.itemChildDepartmentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtil.sendEmail(context,childDepartment.getPhone()," ");
            }
        });
        if (DepManagerLab.get(context).getDepManagerDto()!=null&&childDepartment.getDepartmentId() == DepManagerLab.get(context).getDepManagerDto().getDepartmentId()) {
            childDepartmentHolder.itemChildDepartmentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDialog(childDepartment.getChildDepartmentId());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return childDepartments.size();
    }


    private void showDialog(final long childDepId) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Material Design Dialog");
        builder.setMessage("是否删除该子部门?");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteChild(childDepId);

            }
        });
        builder.show();
    }

    class ChildDepartmentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_child_department_layout)
        LinearLayout itemChildDepartmentLayout;
        @BindView(R.id.item_child_department_name)
        TextView itemChildDepartmentName;
        @BindView(R.id.item_child_department_email)
        TextView itemChildDepartmentEmail;
        @BindView(R.id.item_child_department_message)
        ImageView itemChildDepartmentMessage;
        @BindView(R.id.item_child_department_phone)
        ImageView itemChildDepartmentPhone;
        public ChildDepartmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void deleteChild(final long childDepId) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                int result = DepManagerApi.deleteChildDepartment(context, childDepId);
                subscriber.onNext(result);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Object object) {
                int result=(int)object;
                if (result == 1){
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    context.initData();
                    DepManagerLab.get(context).getDepManagerDto().setChildDepNum(DepManagerLab.get(context).getDepManagerDto().getChildDepNum()-1);
                }else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
