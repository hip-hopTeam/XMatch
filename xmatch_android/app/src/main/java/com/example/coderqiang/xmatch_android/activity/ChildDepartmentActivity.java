package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ChildDepartmentAdapter;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;

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
    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_child_department_name)
    TextView managerChildDepartmentName;

    @BindView(R.id.manager_child_department_add)
    ImageView addChildDepartment;

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

    private void initData() {
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
                Intent intent = new Intent(this, AddChildDepartmentActivtiy.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
