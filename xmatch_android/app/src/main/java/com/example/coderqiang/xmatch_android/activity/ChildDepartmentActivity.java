package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ChildDepartmentAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.api.UserApi;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;
import com.example.coderqiang.xmatch_android.model.DepMember;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class ChildDepartmentActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ChildDepartmentActivity";

    public static final String DEP_ID="departmentId";
    public static final String DEP_NAME="departmentName";


    @BindView(R.id.manager_child_department_recycler)
    RecyclerView managerChildDepartmentRecycler;


    long departmentId = 0;
    String departmentName = "";
    @BindView(R.id.manager_child_department_add_tv)
    TextView managerChildDepartmentAddTv;
    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_child_department_name)
    TextView managerChildDepartmentName;
    @BindView(R.id.manager_child_department_add_layout)
    LinearLayout managerChildDepartmentAddLayout;
    @BindView(R.id.manager_child_department_add)
    public ImageView addChildDepartment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_department);
        setConfig();
        ButterKnife.bind(this);
        departmentId = getIntent().getLongExtra("departmentId", 0);
        departmentName = getIntent().getStringExtra("departmentName");
        initView();
    }

    public void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<ChildDepartment> result = DepManagerApi.getChildDepartments(departmentId);
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
                List<ChildDepartment> childDepartments = (List<ChildDepartment>) object;
                if (childDepartments != null) {
                    managerChildDepartmentRecycler.setAdapter(new ChildDepartmentAdapter(childDepartments, ChildDepartmentActivity.this));
                }
            }
        });
    }

    private void initView() {
        managerChildDepartmentRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        managerChildDepartmentName.setText(departmentName);
        addChildDepartment.setOnClickListener(this);
        managerChildDepartmentAddLayout.setOnClickListener(this);
        if (!DefaultConfig.get(getApplicationContext()).isUser()){
            managerChildDepartmentAddTv.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.manager_back)
    public void onViewClicked() {
        super.onBackPressed();
    }

    private void setConfig() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manager_child_department_add:
                if (!DefaultConfig.get(getApplicationContext()).isUser()) {
                    Intent intent = new Intent(this, AddChildDepartmentActivtiy.class);
                    startActivity(intent);
                }else {
                    addDepartment();
                }
                break;
            case R.id.manager_child_department_add_layout:
                if (DefaultConfig.get(getApplicationContext()).isUser()) {
                    addDepartment();
                }
                break;
        }
    }

    public void addDepartment(){
        AlertDialog.Builder logoutDialog =
                new AlertDialog.Builder(ChildDepartmentActivity.this);
        logoutDialog.setTitle("申请");
        logoutDialog.setMessage("是否以普通部员身份加入<"+departmentName+">?");
        logoutDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Observable.create(new Observable.OnSubscribe<Object>() {
                            @Override
                            public void call(Subscriber<? super Object> subscriber) {
                                DepMember depMember = new DepMember();
                                depMember.setUserId(DefaultConfig.get(getApplicationContext()).getUserId());
                                depMember.setState(DepMember.STATE_APPLY);
                                depMember.setJoinTime(System.currentTimeMillis());
                                depMember.setRole("部员");
                                depMember.setDepId(departmentId);
                                int result = UserApi.addApplyToDepartment(getApplicationContext(), depMember);
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
                                if (result == ResultCode.Companion.getSUCCESS()) {
                                    Toast.makeText(ChildDepartmentActivity.this, "添加申请成功", Toast.LENGTH_SHORT).show();
                                }else if (result==ResultCode.Companion.getDEP_MEMBER_EXIST()){
                                    Toast.makeText(ChildDepartmentActivity.this, "已加入过该部门", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ChildDepartmentActivity.this, "添加申请失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });;
        logoutDialog.setNegativeButton("取消",
                null);
        logoutDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
