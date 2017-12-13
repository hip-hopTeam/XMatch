package com.example.coderqiang.xmatch_android.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.adapter.ActivityAdapter;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.fragment.ActivityFragment;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.PhotoClipperUtil;
import com.example.coderqiang.xmatch_android.util.ResultCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
 * Created by coderqiang on 2017/11/14.
 */

public class ActivityActivity extends Activity {

    public static final String INTENT_ACTIVITY_ID="activityId";
    private static final String TAG="ActivityActivity";

    public static final int SELECT_PIC = 2;
    public static final int SELECT_CLIPPER_PIC = 1;

    @BindView(R.id.activity_detail_back)
    ImageView activityDetailBack;
    @BindView(R.id.manager_add_dep_bar)
    LinearLayout managerAddDepBar;
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
    @BindView(R.id.activity_detail_add_image)
    TextView activityDetailAddImage;
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
        initView();
        initData();
        activityDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {

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
            window.setStatusBarColor(getResources().getColor(R.color.transparent));
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
        activityDetailTime.setText("起:"+dateFormat.format(activity.getStartTime())+ "\n止：" + dateFormat.format(activity.getEndTime()));
        activityDetailTitle.setText(activity.getActivityName());
        activityDetailMeasure.setText(activity.getMeasure()+"");
        activityDetailManager.setText(activity.getDepName()+"");
        Glide.with(this).load(DefaultConfig.BASE_URL+activity.getImageUrl())
                .asBitmap().error(R.drawable.avator)
                .into(activityDetailImage);
        if (activity.getDepId() == DepManagerLab.get(getApplicationContext()).getDepManagerDto().getDepartmentId()) {
            activityDetailAddImage.setVisibility(View.VISIBLE);
            activityDetailAddImage.setOnClickListener(v->{
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    //进行权限请求
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);
                }
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, SELECT_PIC);
            });
        }
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
                clipperBigPic(this, data.getData());
//                saveBitmap(data);
                break;
            case SELECT_CLIPPER_PIC:
                Log.i(TAG, "onActivityResult: saveBitmap");
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(this, getTempFile()));
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, SELECT_CLIPPER_PIC);
    }

    private Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            System.out.println("package:" + this.getPackageName());
            uri = FileProvider.getUriForFile(context.getApplicationContext(), this.getPackageName() + ".fileProvider", file);
        } else {
            uri = Uri.fromFile(file);
        }
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
                        int code = ActivityApi.activityImageUpLoad(file,activityId);
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
                            Toast.makeText(ActivityActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
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
