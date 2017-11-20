package com.example.coderqiang.xmatch_android.activity;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.fragment.DepartmentFragment;
import com.example.coderqiang.xmatch_android.fragment.ManagerMainFragment;
import com.example.coderqiang.xmatch_android.fragment.MemberFragment;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.PhotoClipperUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.coderqiang.xmatch_android.fragment.ManagerMainFragment.SELECT_CLIPPER_PIC;
import static com.example.coderqiang.xmatch_android.fragment.ManagerMainFragment.SELECT_PIC;
import static com.example.coderqiang.xmatch_android.fragment.ManagerMainFragment.getImageCachePath;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class ManagerMainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ManagerMainActivity";

    public static final String IMAGE_FILE_NAME="test.png";

    public ManagerMainFragment managerMainFragment;
    public MemberFragment memberFragment;
    public DepartmentFragment departmentFragment;
    public ActivityFragment activityFragment;
    public Fragment current;

    MenuItem menuItem;
    public NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private int menuSelect = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);
        ButterKnife.bind(this);
        setConfig();
        requestPermission();
        initData();
        initView();
        setStatusBarDarkMode(true,this);
    }


    private void initData() {

//        DefaultConfig.get(getApplicationContext()).setDepmanagerId(6);
    }

    private void initView() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        managerMainFragment = new ManagerMainFragment();
        current = managerMainFragment;
        menuItem = navigationView.getMenu().getItem(menuSelect);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container , current)
                .commit();
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

    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchFragment(Fragment from, Fragment to){
        if(current !=to){
            if(!to.isAdded()){
                getSupportFragmentManager().beginTransaction().add(R.id.main_container,to).hide(from).show(to).commit();
            }else {
                getSupportFragmentManager().beginTransaction().hide(from).show(to).commit();
            }
            current=to;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                if (managerMainFragment == null) {
                    managerMainFragment = new ManagerMainFragment();
                }
                switchFragment(current, managerMainFragment);
                break;
            case R.id.item2:
                if (memberFragment == null) {
                    memberFragment = new MemberFragment();
                }
                switchFragment(current, memberFragment);
                break;
            case R.id.item3:
                if (departmentFragment == null) {
                    departmentFragment = new DepartmentFragment();
                }
                switchFragment(current, departmentFragment);
                break;
            case R.id.item4:
                if (activityFragment == null) {
                    activityFragment = new ActivityFragment();
                }
                switchFragment(current, activityFragment);
                break;
            case R.id.item9:
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawer.closeDrawers();
        return true;
    }

    public void requestPermission(){
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this,"please give me the permission",Toast.LENGTH_SHORT).show();
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS},
                        1);
            }
        }
    }

}

