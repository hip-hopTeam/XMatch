package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;

import java.text.SimpleDateFormat;
import java.util.List;

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

public class ActivityActivity extends Activity {

    public static final String INTENT_ACTIVITY_ID="activityId";
    private static final String TAG="ActivityActivity";

    @BindView(R.id.activity_detail_back)
    ImageView activityDetailBack;
    @BindView(R.id.manager_add_dep_bar)
    AppBarLayout managerAddDepBar;
    @BindView(R.id.activity_detail_image)
    ImageView activityDetailImage;
    @BindView(R.id.activity_detail_title)
    TextView activityDetailTitle;
    @BindView(R.id.activity_detail_content)
    TextView activityDetailContent;
    @BindView(R.id.activity_detail_address)
    TextView activityDetailAddress;
    @BindView(R.id.activity_detail_time)
    TextView activityDetailTime;
    @BindView(R.id.activity_detail_manager)
    TextView activityDetailManager;
    @BindView(R.id.add_dep_name_tv)
    LinearLayout addDepNameTv;
    @BindView(R.id.activity_detail_measure)
    TextView activityDetailMeasure;

    long activityId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        setConfig();
        ButterKnife.bind(this);
        activityId=getIntent().getLongExtra("activityId",0);
        initData();
        activityDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void setConfig() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                com.example.coderqiang.xmatch_android.model.Activity activity = ActivityApi.getActivityDetail(getApplicationContext(),activityId);
                subscriber.onNext(activity);
                if (activity == null) {
                    subscriber.onCompleted();
                }else {
                    subscriber.onNext(activity);
                }
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
                com.example.coderqiang.xmatch_android.model.Activity activity = (com.example.coderqiang.xmatch_android.model.Activity) object;
                showData(activity);
            }
        });
    }

    private void showData(com.example.coderqiang.xmatch_android.model.Activity activity) {
        activityDetailAddress.setText(activity.getAddress()+"");
        activityDetailContent.setText(activity.getContent()+"");
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM月dd日 HH:mm");
        activityDetailTime.setText(dateFormat.format(activity.getStartTime())+ " - " + dateFormat.format(activity.getEndTime()));
        activityDetailTitle.setText(activity.getActivityName());
        activityDetailMeasure.setText(activity.getMeasure()+"");
        activityDetailManager.setText(activity.getDepName()+"");
        Glide.with(this).load(DefaultConfig.BASE_URL+activity.getImageUrl())
                .asBitmap().error(R.drawable.avator)
                .into(activityDetailImage);
    }

}
