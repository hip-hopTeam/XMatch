package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.MainActivity;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.RegexUtil;
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
 * Created by coderqiang on 2017/11/12.
 */

public class AddDepartmentActivity extends Activity  {

    private static final String TAG = "AddDepartmentActivity";

    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_main_search)
    ImageView managerMainSearch;
    @BindView(R.id.manager_add_dep_bar)
    AppBarLayout managerAddDepBar;
    @BindView(R.id.manager_add_dep_name)
    EditText managerAddDepName;
    @BindView(R.id.manager_add_dep_summary)
    EditText managerAddDepSummary;
    @BindView(R.id.manager_add_dep_phone)
    EditText managerAddDepPhone;
    @BindView(R.id.add_dep_name_tv)
    LinearLayout addDepNameTv;
    @BindView(R.id.manager_add_dep_save)
    RelativeLayout managerAddDepSave;
    @BindView(R.id.manager_add_dep_layout)
    ConstraintLayout managerAddDepLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_department);
        WindowUtil.setConfig(this);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.manager_back, R.id.manager_add_dep_save})
    public void onViewClicked(View view) {
        Log.i(TAG, "onViewClicked: "+view.getId());
        switch (view.getId()) {
            case R.id.manager_back:
                super.onBackPressed();
                break;
            case R.id.manager_add_dep_save:
                if (RegexUtil.isEmpty(managerAddDepName.getText().toString())){
                    RegexUtil.showToast(this, "部门名字不能为空!");
                    return;
                }
                if (!RegexUtil.isPhone(managerAddDepPhone.getText().toString())) {
                    RegexUtil.showToast(this, "手机格式不正确!");
                    return;
                }
                Department department = new Department();
                department.setDepManagerId(DefaultConfig.get(getApplicationContext()).getDepmanagerId());
                department.setDepName(managerAddDepName.getText().toString());
                department.setDepSummary(managerAddDepSummary.getText().toString());
                department.setEmergencyPhone(managerAddDepPhone.getText().toString());
                addDepartment(department);
                break;
        }
    }

    private void addDepartment(final Department department) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                BaseMessage message = DepManagerApi.addDepartment(department);
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
                if (message.code == ResultCode.Companion.getSUCCESS()) {
                    Toast.makeText(getApplicationContext(), "已经提交审核", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddDepartmentActivity.this, ManagerMainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
