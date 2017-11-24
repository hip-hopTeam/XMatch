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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.NoticeApi;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.model.AppNotice;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/11/24.
 */

public class AddNoticeActivity extends Activity {

    @BindView(R.id.notice_add_back)
    ImageView noticeAddBack;
    @BindView(R.id.notice_add_bar)
    AppBarLayout noticeAddBar;
    @BindView(R.id.notice_add_title)
    EditText noticeAddTitle;
    @BindView(R.id.notice_add_type)
    TextView noticeAddType;
    @BindView(R.id.notice_add_content)
    EditText noticeAddContent;
    @BindView(R.id.manager_add_activity_save)
    RelativeLayout managerAddActivitySave;

    int type=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_add);
        ButterKnife.bind(this);
        WindowUtil.setConfig(this);
        initData();
        initView();
    }

    private void initView() {
        noticeAddType.setText("部门通知");
    }

    private void initData() {

    }

    @OnClick({R.id.notice_add_back, R.id.notice_add_type, R.id.manager_add_activity_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_add_back:
                super.onBackPressed();
                break;
            case R.id.notice_add_type:
                OptionsPickerView pickerView=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        if (options1 == 0) {
                            type=2;
                            noticeAddType.setText("全校通知");
                        } else {
                            noticeAddType.setText("部门通知");
                            type=1;
                        }
                    }
                })
                        .setSelectOptions(0)
                        .setTitleText("当前周")
                        .build();
                List<String> types = new ArrayList<>();
                types.add("全校");
                types.add("部门");
                pickerView.setPicker(types);
                pickerView.show(true);
                break;
            case R.id.manager_add_activity_save:
                addNotice();
                break;
        }
    }

    private void addNotice() {
        final AppNotice appNotice = new AppNotice();
        appNotice.setContent(noticeAddContent.getText().toString());
        appNotice.setCreateTime(System.currentTimeMillis());
        appNotice.setDeparmentName(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepName());
        appNotice.setTitle(noticeAddTitle.getText().toString());
        appNotice.setDepartmentId(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepartmentId());
        appNotice.setType(1);
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                IntResultMessage result= NoticeApi.addNotice(appNotice);
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
                IntResultMessage message=(IntResultMessage)object;
                String result = "";
                if (message == null) {
                    result = "访问服务器失败";
                }else {
                    if (message.code == ResultCode.Companion.getSUCCESS()) {
                        result="添加成功";
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddNoticeActivity.this, ActivityActivity.class);
                        System.out.println("id:"+message.getObject());
                        intent.putExtra(ActivityActivity.INTENT_ACTIVITY_ID, message.getObject());
                        startActivity(intent);
                        finish();
                        return;
                    }else{
                        result="添加失败";
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
