package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/25.
 */

public class EditDepartmentActivity extends Activity {

    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_main_search)
    ImageView managerMainSearch;
    @BindView(R.id.edit_summary_bar)
    AppBarLayout editSummaryBar;
    @BindView(R.id.dep_edit_name)
    TextView depEditName;
    @BindView(R.id.dep_edit_summary)
    EditText depEditSummary;
    @BindView(R.id.dep_edit_phone)
    EditText depEditPhone;
    @BindView(R.id.add_dep_name_tv)
    LinearLayout addDepNameTv;
    @BindView(R.id.dep_edit_save)
    RelativeLayout depEditSave;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_summary);
        ButterKnife.bind(this);
        WindowUtil.setConfig(this);
        initView();
    }

    private void initView() {
        DepManagerDto depManagerDto = DepManagerLab.get(getApplicationContext()).getDepManagerDto();
        depEditName.setText(depManagerDto.getDepName()+"(一经申请无法修改)");
        depEditPhone.setText(depManagerDto.getEmergencyPhone());
        depEditSummary.setText(depManagerDto.getDepSummary());
    }

    @OnClick({R.id.manager_back, R.id.dep_edit_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_back:
                super.onBackPressed();
                break;
            case R.id.dep_edit_save:

                break;
        }
    }

    private void updateDepartment(){
        Department department = new Department();
        department.setEmergencyPhone(depEditPhone.getText().toString());
        department.setDepSummary(depEditSummary.getText().toString());
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                BaseMessage message = DepManagerApi.updateDepartment(department);
                subscriber.onNext(message);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "服务器出现异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Object object) {
                BaseMessage message = (BaseMessage) object;
                if (message.code == ResultCode.Companion.getSUCCESS()) {
                    Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
                    DepManagerDto depManagerDto = DepManagerLab.get(getApplicationContext()).getDepManagerDto();
                    depManagerDto.setEmergencyPhone(department.getEmergencyPhone());
                    depManagerDto.setDepSummary(department.getDepSummary());
                }else {
                    Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_SHORT).show();
                }
                EditDepartmentActivity.super.onBackPressed();
            }
        });
    }
}
