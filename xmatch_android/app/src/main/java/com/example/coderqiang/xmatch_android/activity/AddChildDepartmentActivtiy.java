package com.example.coderqiang.xmatch_android.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class AddChildDepartmentActivtiy extends Activity {

    private static final String TAG="AddChildDepartment";

    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_main_search)
    ImageView managerMainSearch;
    @BindView(R.id.manager_add_child_bar)
    AppBarLayout managerAddChildBar;
    @BindView(R.id.manager_add_child_name)
    EditText managerAddChildName;
    @BindView(R.id.manager_add_child_email)
    EditText managerAddChildEmail;
    @BindView(R.id.manager_add_child_phone)
    EditText managerAddChildPhone;
    @BindView(R.id.add_child_name_tv)
    LinearLayout addChildNameTv;
    @BindView(R.id.manager_add_child_save)
    RelativeLayout managerAddChildSave;
    @BindView(R.id.manager_add_child_layout)
    ConstraintLayout managerAddChildLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtity_main_add_child_department);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.manager_back, R.id.manager_add_child_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_back:
                break;
            case R.id.manager_add_child_save:
                postData();
                break;
        }
    }

    private void postData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                ChildDepartment childDepartment = new ChildDepartment();
                childDepartment.setDepartmentId(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepartmentId());
                childDepartment.setEmail(managerAddChildEmail.getText().toString());
                childDepartment.setName(managerAddChildName.getText().toString());
                childDepartment.setPhone(managerAddChildPhone.getText().toString());
                BaseMessage message= DepManagerApi.addChildDepartment(childDepartment);
                subscriber.onNext(message);

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
                BaseMessage message = (BaseMessage) object;
                if (message != null) {
                    if (message.getCode() == ResultCode.Companion.getSUCCESS()) {
                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        DepManagerDto depManagerDto=DepManagerLab.get(AddChildDepartmentActivtiy.this).getDepManagerDto();
                        depManagerDto.setChildDepNum(depManagerDto.getChildDepNum()+1);
                        AddChildDepartmentActivtiy.this.onBackPressed();
                    }else {
                        Toast.makeText(getApplicationContext(), message.getResult(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
