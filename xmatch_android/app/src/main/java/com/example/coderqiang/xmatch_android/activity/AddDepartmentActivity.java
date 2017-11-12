package com.example.coderqiang.xmatch_android.activity;

import android.app.Activity;
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

import com.example.coderqiang.xmatch_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class AddDepartmentActivity extends Activity implements View.OnClickListener {

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
    @BindView(R.id.manager_add_dep_image)
    ImageView managerAddDepImage;
    @BindView(R.id.manager_add_dep_image_tv)
    TextView managerAddDepImageTv;
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
        setConfig();
        ButterKnife.bind(this);
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
        Log.i(TAG, "onClick: "+view.getId());
        switch (view.getId()) {
            case R.id.manager_back:
                super.onBackPressed();
                break;
            case R.id.manager_add_dep_image:

                break;
            case R.id.manager_add_dep_save:

                break;
        }
    }

    @OnClick({R.id.manager_back, R.id.manager_add_dep_image, R.id.manager_add_dep_image_tv, R.id.manager_add_dep_save})
    public void onViewClicked(View view) {
        Log.i(TAG, "onViewClicked: "+view.getId());
        switch (view.getId()) {
            case R.id.manager_back:
                break;
            case R.id.manager_add_dep_image:
                break;
            case R.id.manager_add_dep_image_tv:
                break;
            case R.id.manager_add_dep_save:
                break;
        }
    }
}
