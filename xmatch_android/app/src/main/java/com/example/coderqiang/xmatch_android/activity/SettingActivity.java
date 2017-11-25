package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by coderqiang on 2017/11/25.
 */

public class SettingActivity extends Activity {

    @BindView(R.id.activity_detail_back)
    ImageView activityDetailBack;
    @BindView(R.id.manager_add_dep_bar)
    AppBarLayout managerAddDepBar;
    @BindView(R.id.edit_summary)
    RelativeLayout editSummary;
    @BindView(R.id.post_advice)
    RelativeLayout postAdvice;
    @BindView(R.id.update_searche)
    RelativeLayout updateSearche;
    @BindView(R.id.share_app)
    RelativeLayout shareApp;
    @BindView(R.id.about_us)
    RelativeLayout aboutUs;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.logout_button)
    RelativeLayout logoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        WindowUtil.setConfig(this);
    }

    @OnClick({R.id.activity_detail_back, R.id.edit_summary, R.id.post_advice, R.id.update_searche, R.id.share_app, R.id.about_us, R.id.logout_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_detail_back:
                super.onBackPressed();
                break;
            case R.id.edit_summary:
                Intent editIntent = new Intent(this, EditDepartmentActivity.class);
                startActivity(editIntent);
                break;
            case R.id.post_advice:
                AlertDialog.Builder postAdviceDialog =
                        new AlertDialog.Builder(SettingActivity.this);
                postAdviceDialog.setTitle("意见反馈");
                postAdviceDialog.setMessage("该功能还未完善\n如遇问题,请联系 976928202@qq.com!");
                postAdviceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });;
                postAdviceDialog.show();
                break;
            case R.id.update_searche:
                break;
            case R.id.share_app:
                break;
            case R.id.about_us:
                AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(SettingActivity.this);
                normalDialog.setTitle("关于我们 ");
                normalDialog.setMessage("由福州大学2017z班\"我是嘻哈我是侠\"提供技术支持 \n如遇问题，请联系 976928202@qq.com!");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });;
                normalDialog.show();
                break;
            case R.id.logout_button:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
                break;
        }
    }
}
