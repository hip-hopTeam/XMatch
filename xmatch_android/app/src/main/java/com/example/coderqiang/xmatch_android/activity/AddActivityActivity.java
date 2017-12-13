package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.dto.DepManagerMessage;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.PhoneUtil;
import com.example.coderqiang.xmatch_android.util.RegexUtil;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CoderQiang on 2017/11/16.
 */

public class AddActivityActivity extends Activity {

    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_main_search)
    ImageView managerMainSearch;
    @BindView(R.id.manager_add_activity_bar)
    AppBarLayout managerAddActivityBar;
    @BindView(R.id.manager_add_activity_name)
    EditText managerAddActivityName;
    @BindView(R.id.manager_add_activity_summary)
    EditText managerAddActivitySummary;
    @BindView(R.id.manager_add_activity_phone)
    EditText managerAddActivityPhone;
    @BindView(R.id.manager_add_activity_measure)
    EditText managerAddActivityMeasure;
    @BindView(R.id.manager_add_activity_startTime)
    TextView managerAddActivityStartTime;
    @BindView(R.id.manager_add_activity_endTime)
    TextView managerAddActivityEndTime;
    @BindView(R.id.manager_add_activity_save)
    RelativeLayout managerAddActivitySave;
    @BindView(R.id.manager_add_activity_address)
    EditText managerAddActivityAddress;

    TimePickerView pvStartTime;
    TimePickerView pvEndTime;
    long startTime=0l;
    long endTime=0l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add);
        WindowUtil.setConfig(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.manager_back, R.id.manager_add_activity_startTime, R.id.manager_add_activity_endTime, R.id.manager_add_activity_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manager_back:
                onBackPressed();
                break;
            case R.id.manager_add_activity_startTime:
               pvStartTime= new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        managerAddActivityStartTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                        startTime=date.getTime();
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build();
                pvStartTime.show();
                break;
            case R.id.manager_add_activity_endTime:
                pvEndTime= new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        managerAddActivityEndTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                        endTime=date.getTime();
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build();
                pvEndTime.show();
                break;
            case R.id.manager_add_activity_save:
                if (startTime==0l||endTime==0l){
                    Toast.makeText(getApplicationContext(),"未选择起止时间!!!",Toast.LENGTH_SHORT).show();
                }else {
                    addActivity();
                }
                break;
        }
    }

    private void addActivity() {
        final com.example.coderqiang.xmatch_android.model.Activity activity=new com.example.coderqiang.xmatch_android.model.Activity();
        if (managerAddActivityName.getText().toString().length() <= 0) {
            RegexUtil.showToast(this,"活动名不能为空!");
            return;
        }
        activity.setActivityName(managerAddActivityName.getText().toString());
        if (managerAddActivityAddress.getText().toString().length() <= 0) {
            RegexUtil.showToast(this,"地点不能为空!");
            return;
        }
        activity.setAddress(managerAddActivityAddress.getText().toString());
        activity.setDepName(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepName());
        activity.setContent(managerAddActivitySummary.getText().toString());
        if (endTime >= startTime) {
            RegexUtil.showToast(this,"截止时间一定要大于开始时间!");
            return;
        }
        activity.setStartTime(startTime);
        activity.setEndTime(endTime);
        activity.setDepId(DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepartmentId());
        if (!RegexUtil.isPhone(managerAddActivityPhone.getText().toString())) {
            RegexUtil.showToast(this,"手机格式不正确!");
            return;
        }
        activity.setManagerPhone(managerAddActivityPhone.getText().toString());
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                IntResultMessage result= ActivityApi.addActivity(activity);
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
                        Intent intent = new Intent(AddActivityActivity.this, ActivityActivity.class);
                        System.out.println("id:"+message.getObject());
                        intent.putExtra(ActivityActivity.INTENT_ACTIVITY_ID, message.getObject());
                        DepManagerDto depManagerDto = DepManagerLab.get(getApplicationContext()).getDepManagerDto();
                        depManagerDto.setActivityNum(depManagerDto.getActivityNum() + 1);
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
