package com.example.coderqiang.xmatch_android.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.activity.ActivityListActivity;
import com.example.coderqiang.xmatch_android.activity.ChildDepartmentActivity;
import com.example.coderqiang.xmatch_android.activity.UserMainActivity;
import com.example.coderqiang.xmatch_android.activity.MessageActivity;
import com.example.coderqiang.xmatch_android.activity.SettingActivity;
import com.example.coderqiang.xmatch_android.activity.UserMainActivity;
import com.example.coderqiang.xmatch_android.adapter.UserDepartmentAdapter;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.api.UserApi;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.dto.UserDto;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.PhotoClipperUtil;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.UserDtoLab;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.coderqiang.xmatch_android.activity.UserMainActivity.IMAGE_FILE_NAME;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class UserMainFragment extends Fragment{

    private static final String TAG = "ManagerMainFragment";
    public static final int SELECT_PIC = 2;
    public static final int SELECT_CLIPPER_PIC = 1;

    boolean isFirst = true;
    DrawerLayout drawer;

    @BindView(R.id.user_main_refresh)
    ImageView refreshBtn;
    @BindView(R.id.user_main_menu)
    ImageView userMainMenu;
    @BindView(R.id.user_main_dep_name)
    TextView userMainDepName;
    @BindView(R.id.user_main_bar)
    AppBarLayout userMainBar;
    @BindView(R.id.user_main_avator)
    CircleImagview userMainAvator;
    @BindView(R.id.user_main_name)
    TextView userMainName;
    @BindView(R.id.user_main_setting_btn)
    ImageView userMainSettingBtn;
    @BindView(R.id.user_main_summary_tv)
    TextView userMainSummaryTv;
    @BindView(R.id.user_main_recycler)
    RecyclerView userMainRecycler;

    @BindView(R.id.user_main_college)
    TextView userMainCollege;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_main, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst && UserDtoLab.get(getActivity()).getUserDto() != null) {
            show(UserDtoLab.get(getActivity()).getUserDto());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (UserDtoLab.get(getActivity()).getUserDto() != null) {
                show(UserDtoLab.get(getActivity()).getUserDto());
            }
        }
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                UserDto userDto = UserApi.getUser(DefaultConfig.get(getActivity()).getUserId());
                subscriber.onNext(userDto);
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
                UserDto userDto = (UserDto) object;
                UserDtoLab.get(getActivity().getApplicationContext()).setUserDto(userDto);
                System.out.println("userId" +  UserDtoLab.get(getActivity().getApplicationContext()).getUserDto().getUserId());
                if (userDto != null) {
                    isFirst = false;
                    show(userDto);
                } else {
                    Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                }

            }
        });

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<Department> departments = UserApi.getUserDeps(DefaultConfig.get(getActivity()).getUserId());
                subscriber.onNext(departments);
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
                List<Department> departments = (List<Department>) object;
                userMainRecycler.setAdapter(new UserDepartmentAdapter(getActivity(),departments));
            }
        });
    }

    private void show(UserDto userDto) {
        userMainName.setText(userDto.getUsername());
        userMainSummaryTv.setText(userDto.getSummary());
        userMainCollege.setText(userDto.getCollege());
        ((TextView) (getActivity().findViewById(R.id.nav_header_name))).setText(userDto.getUsername() + "");
        Glide.with(this).load(DefaultConfig.BASE_URL + userDto.getAvatorUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).error(R.drawable.avator)
                .into(userMainAvator);
        Glide.with(this).load(DefaultConfig.BASE_URL + userDto.getAvatorUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).error(R.drawable.avator)
                .into(((CircleImagview) ((UserMainActivity) getActivity()).navigationView.findViewById(R.id.nav_header_dep_avator)));
        System.out.println(userDto.getAvatorUrl());
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        userMainRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    @OnClick({R.id.user_main_menu, R.id.user_main_avator,R.id.user_main_refresh})
    public void onClick(View view) {
        UserMainActivity userMainActivity = (UserMainActivity) getActivity();
        switch (view.getId()) {
            case R.id.user_main_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.user_main_refresh:
                RotateAnimation rotateAnimation = new RotateAnimation(0, -360, refreshBtn.getPivotX(), refreshBtn.getPivotY());
                rotateAnimation.setDuration(1500l);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                refreshBtn.startAnimation(rotateAnimation);
                initData();
                break;
            case R.id.manager_main_avator:
                if (ContextCompat.checkSelfPermission(this.getActivity(),
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("权限没给1");
                    //进行权限请求
                    ActivityCompat.requestPermissions(this.getActivity(),
                            new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);
                }
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, SELECT_PIC);
                break;
            case R.id.manager_main_setting_btn:
                Intent settingIntent = new Intent(getActivity(), SettingActivity.class);
                startActivity(settingIntent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case SELECT_PIC:
                //获取图片后裁剪图片
                clipperBigPic(getActivity(), data.getData());
                break;
            case SELECT_CLIPPER_PIC:
                //获取图片后保存图片到本地，是否需要保存看情况而定
                saveBitmap(data);
                //显示图片
                break;
        }
    }

    private void clipperBigPic(Context context, Uri uri) {
        if (null == uri) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String url = PhotoClipperUtil.getPath(context, uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        }
        //发送裁剪命令
        intent.putExtra("crop", true);
        //X方向上的比例
        intent.putExtra("aspectX", 1);
        //Y方向上的比例
        intent.putExtra("aspectY", 1);
        //裁剪区的宽
        intent.putExtra("outputX", 124);
        //裁剪区的高
        intent.putExtra("outputY", 124);
        //是否保留比例
        intent.putExtra("scale", true);
        //返回数据
        intent.putExtra("return-data", true);
        //输出图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        //裁剪图片保存位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(getActivity(), getTempFile()));
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, SELECT_CLIPPER_PIC);
    }

    private Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri = Uri.fromFile(file);
        return uri;
    }

    /**
     * 临时图片保存路径
     *
     * @return
     */
    private File getTempFile() {
        File file = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void saveBitmap(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            final File file = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Observable.create(new Observable.OnSubscribe<Object>() {

                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        int code = UserApi.imageUpLoad(file,UserDtoLab.get(getActivity()).getUserDto().getUserId());
                        subscriber.onNext(code);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Object o) {
                        int result = (int) o;
                        if (result == ResultCode.Companion.getSUCCESS()) {
                            initData();
                            Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
