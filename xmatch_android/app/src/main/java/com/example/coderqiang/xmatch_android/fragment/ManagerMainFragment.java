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
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
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
import com.example.coderqiang.xmatch_android.activity.AddDepartmentActivity;
import com.example.coderqiang.xmatch_android.activity.AlbumActivity;
import com.example.coderqiang.xmatch_android.activity.ChildDepartmentActivity;
import com.example.coderqiang.xmatch_android.activity.EditDepartmentActivity;
import com.example.coderqiang.xmatch_android.activity.ManagerMainActivity;
import com.example.coderqiang.xmatch_android.activity.MessageActivity;
import com.example.coderqiang.xmatch_android.activity.SettingActivity;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.PhotoClipperUtil;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.SwtichActivityUtil;
import com.example.coderqiang.xmatch_android.view.CircleImagview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.coderqiang.xmatch_android.activity.ManagerMainActivity.IMAGE_FILE_NAME;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class ManagerMainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ManagerMainFragment";
    public static final int SELECT_PIC = 2;
    public static final int SELECT_CLIPPER_PIC = 1;

    @BindView(R.id.manager_main_menu)
    ImageView menuBtn;
    @BindView(R.id.manager_add_dep_refresh)
    ImageView refreshBtn;


    @BindView(R.id.manager_main_dep_name)
    TextView managerMainDepName;
    @BindView(R.id.manager_main_avator)
    CircleImagview managerMainAvator;
    @BindView(R.id.manager_main_name)
    TextView managerMainName;
    @BindView(R.id.manager_main_setting_btn)
    ImageView managerMainSettingBtn;
    @BindView(R.id.manager_main_summary_tv)
    TextView managerMainSummaryTv;
    @BindView(R.id.manager_main_member_num)
    TextView managerMainMemberNum;
    @BindView(R.id.manager_main_activity_num)
    TextView managerMainActivityNum;
    @BindView(R.id.manager_main_child_num)
    TextView managerMainChildNum;
    @BindView(R.id.manager_main_bar)
    AppBarLayout managerMainBar;

    boolean isFirst = true;

    DrawerLayout drawer;
    @BindView(R.id.manager_main_notice)
    LinearLayout managerMainNotice;
    @BindView(R.id.manager_main_paiban)
    LinearLayout managerMainPaiban;
    @BindView(R.id.manager_main_activity)
    LinearLayout managerMainActivity;
    @BindView(R.id.manager_main_album)
    LinearLayout managerMainAlbum;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_main, container, false);
        ButterKnife.bind(this, view);
        menuBtn = view.findViewById(R.id.manager_main_menu);
        initData();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst && DepManagerLab.get(getActivity()).getDepManagerDto() != null) {
            show(DepManagerLab.get(getActivity()).getDepManagerDto());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (DepManagerLab.get(getActivity()).getDepManagerDto() != null) {
                show(DepManagerLab.get(getActivity()).getDepManagerDto());
            }
        }
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                DepManagerDto depManager = DepManagerApi.getDepmanager(DefaultConfig.get(getActivity()).getDepmanagerId());
                subscriber.onNext(depManager);
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
                DepManagerDto depManagerDto = (DepManagerDto) object;
                DepManagerLab.get(getActivity().getApplicationContext()).setDepManagerDto(depManagerDto);
                System.out.println("depId" + DepManagerLab.get(getActivity().getApplicationContext()).getDepManagerDto().getDepartmentId());
                if (depManagerDto != null) {
                    isFirst = false;
                    show(depManagerDto);
                } else {
                    Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void show(DepManagerDto depManagerDto) {
        managerMainName.setText(depManagerDto.getManagerName());
        managerMainSummaryTv.setText(depManagerDto.getDepSummary());
        managerMainActivityNum.setText(depManagerDto.getActivityNum() + "");
        managerMainChildNum.setText(depManagerDto.getChildDepNum() + "");
        managerMainMemberNum.setText(depManagerDto.getMemberNum() + "");
        managerMainDepName.setText(depManagerDto.getDepName() + "");
        ((TextView) (getActivity().findViewById(R.id.nav_header_name))).setText(depManagerDto.getDepName() + "");
        ((TextView) (getActivity().findViewById(R.id.nav_header_role))).setText(depManagerDto.getRole() + "");

        managerMainMemberNum.setOnClickListener(this);
        managerMainChildNum.setOnClickListener(this);
        managerMainActivityNum.setOnClickListener(this);
        managerMainAvator.setOnClickListener(this);
        Glide.with(this).load(DefaultConfig.BASE_URL + depManagerDto.getAvatorUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).error(R.drawable.avator)
                .into(managerMainAvator);
        Glide.with(this).load(DefaultConfig.BASE_URL + depManagerDto.getImageUrl())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).error(R.drawable.avator)
                .into(((CircleImagview) ((ManagerMainActivity) getActivity()).navigationView.findViewById(R.id.nav_header_dep_avator)));
        System.out.println(depManagerDto.getAvatorUrl());
    }

    private void initView() {
        drawer = getActivity().findViewById(R.id.drawer_layout);
        menuBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @OnClick({R.id.manager_main_notice, R.id.manager_main_paiban, R.id.manager_main_activity, R.id.manager_main_album,R.id.manager_main_setting_btn})
    public void onClick(View view) {
        ManagerMainActivity managerMainActivity = (ManagerMainActivity) getActivity();
        switch (view.getId()) {
            case R.id.manager_main_menu:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.manager_add_dep_refresh:
                RotateAnimation rotateAnimation=new RotateAnimation(0,-360,refreshBtn.getPivotX(), refreshBtn.getPivotY());
                rotateAnimation.setDuration(1500l);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                refreshBtn.startAnimation(rotateAnimation);
                initData();
                break;
            case R.id.manager_main_child_num:
                Intent intent = new Intent(getActivity(), ChildDepartmentActivity.class);
                DepManagerDto depManagerDto = DepManagerLab.get(getActivity()).getDepManagerDto();
                intent.putExtra(ChildDepartmentActivity.DEP_NAME, depManagerDto.getDepName());
                intent.putExtra(ChildDepartmentActivity.DEP_ID, depManagerDto.getDepartmentId());
                startActivity(intent);
                break;
            case R.id.manager_main_avator:
                if (ContextCompat.checkSelfPermission(this.getActivity(),
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this.getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    System.out.println("权限没给1");
                        //进行权限请求
                    ActivityCompat.requestPermissions(this.getActivity(),
                                new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                2);
                }
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, SELECT_PIC);
                break;
            case R.id.manager_main_member_num:
                if (managerMainActivity.memberFragment == null) {
                    managerMainActivity.memberFragment = new MemberFragment();
                }
                managerMainActivity.switchFragment(managerMainActivity.current, managerMainActivity.memberFragment);
                break;
            case R.id.manager_main_activity_num:
                Intent activityListIntent = new Intent(getActivity(), ActivityListActivity.class);
                startActivity(activityListIntent);
                break;
            case R.id.manager_main_notice:
                Intent messageIntent = new Intent(getActivity(), MessageActivity.class);
                startActivity(messageIntent);
                break;
            case R.id.manager_main_setting_btn:
                Intent settingIntent = new Intent(getActivity(), SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.manager_main_paiban:
                Toast.makeText(getActivity(), "此功能即将来袭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.manager_main_activity:
                Intent activityListIntent2 = new Intent(getActivity(), ActivityListActivity.class);
                startActivity(activityListIntent2);
                break;
            case R.id.manager_main_album:
                Intent albumIntent = new Intent(getActivity(), AlbumActivity.class);
                albumIntent.putExtra("depId", DepManagerLab.get(getActivity()).getDepManagerDto().getDepartmentId());
                albumIntent.putExtra("uploadName", DepManagerLab.get(getActivity()).getDepManagerDto().getDepName());
                albumIntent.putExtra("title", DepManagerLab.get(getActivity()).getDepManagerDto().getDepName());
                startActivity(albumIntent);
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
//                saveBitmap(data);
                break;
            case SELECT_CLIPPER_PIC:
                //获取图片后保存图片到本地，是否需要保存看情况而定
                saveBitmap(data);
                //显示图片
                break;
        }
    }

    public static String getImageCachePath()//给图片一个存储路径
    {
        String sdRoot = File.separator+"mnt"+File.separator+"sdcard";
        String result = sdRoot;
        if (new File(result).exists() && new File(result).isDirectory()) {
            return result;
        } else {
            return sdRoot;
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
                        int code = DepManagerApi.imageUpLoad(file, DepManagerLab.get(getActivity()).getDepManagerDto().getDepManagerId());
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
