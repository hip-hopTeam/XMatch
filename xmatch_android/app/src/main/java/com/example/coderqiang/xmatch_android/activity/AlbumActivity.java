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
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.ActivityApi;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;
import com.example.coderqiang.xmatch_android.model.DepartmentAlbum;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.PhotoClipperUtil;
import com.example.coderqiang.xmatch_android.util.RegexUtil;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.WindowUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
 * Created by coderqiang on 2017/12/14.
 */

public class AlbumActivity extends Activity {
    private static final String TAG = "AlbumActivity";

    @BindView(R.id.album_back)
    ImageView albumBack;
    @BindView(R.id.album_add)
    ImageView albumAdd;
    @BindView(R.id.album_activity_bar)
    AppBarLayout albumActivityBar;
    @BindView(R.id.album_recycler)
    RecyclerView albumRecycler;
    @BindView(R.id.album_refresh)
    TwinklingRefreshLayout albumRefresh;
    @BindView(R.id.album_title)
    TextView albumTitle;

    public static final int SELECT_PIC = 2;
    public static final int SELECT_CLIPPER_PIC = 1;

    List<DepartmentAlbum> albums = new ArrayList<>();

    long depId = 0;
    long activityId = 0;
    String uploadName = "";
    String title = "活动相册";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        depId = getIntent().getLongExtra("depId", 0);
        activityId = getIntent().getLongExtra("activityId", 0);
        uploadName=getIntent().getStringExtra("uploadName");
        title = getIntent().getStringExtra("title");
        initView();
        initData();

    }

    private void initView() {
        albumTitle.setText(title);
        albumRecycler.setLayoutManager(new GridLayoutManager(this,2));
        albumRefresh.setHeaderView(new SinaRefreshView(this));
        albumRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                initData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
            }
        });
    }

    public void initData() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<DepartmentAlbum> result = new ArrayList<>();
                if (depId != 0) {
                    result = DepManagerApi.getDeprtmentAlbumByDepId(AlbumActivity.this, depId);
                } else {
                    result= DepManagerApi.getDeprtmentAlbumByActivity(AlbumActivity.this,activityId);
                }
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
                albums= (List<DepartmentAlbum>) object;
                if (albums != null) {
                    albumRecycler.setAdapter(new AlbumAdapter());
                }
            }
        });
    }

    @OnClick({R.id.album_back, R.id.album_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.album_back:
                onBackPressed();
                break;
            case R.id.album_add:
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    System.out.println("权限没给1");
                    //进行权限请求
                    ActivityCompat.requestPermissions(AlbumActivity.this,
                            new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);
                }
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, SELECT_PIC);
                break;
        }
    }

    class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

        @Override
        public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AlbumViewHolder(LayoutInflater.from(AlbumActivity.this).inflate(R.layout.item_album, parent, false));
        }

        @Override
        public void onBindViewHolder(AlbumViewHolder holder, int position) {
            DepartmentAlbum album = albums.get(position);
            Glide.with(AlbumActivity.this).load(DefaultConfig.BASE_URL+album.getImageUrl())
                    .asBitmap().error(R.drawable.avator)
                    .into(holder.albumImage);
            holder.albumDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(Subscriber<? super Object> subscriber) {
                            int result=ActivityApi.deleteAlbum(AlbumActivity.this, album.getDepartmentAlbumId());
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
                           int reslut= (int) object;
                            if (reslut==ResultCode.Companion.getSUCCESS()) {
                                RegexUtil.showToast(AlbumActivity.this, "删除成功");
                            }else {
                                RegexUtil.showToast(AlbumActivity.this, "删除失败");
                            }
                        }
                    });

                }
            });
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }

        class AlbumViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.album_image)
            ImageView albumImage;
            @BindView(R.id.album_name)
            TextView albumName;
            @BindView(R.id.album_time)
            TextView albumTime;
            @BindView(R.id.album_delete)
            ImageView albumDelete;

            public AlbumViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
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
                clipperBigPic(getApplicationContext(), data.getData());
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(getApplicationContext(), getTempFile()));
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
                        DepartmentAlbum departmentAlbum = new DepartmentAlbum();
                        departmentAlbum.setActivityId(activityId);
                        departmentAlbum.setDepId(depId);
                        departmentAlbum.setUploadUserName(uploadName);
                        ObjectMessage<Long> message= ActivityApi.addAlbum(getApplicationContext(),departmentAlbum);
                        if (message!=null&&message.getCode() == ResultCode.Companion.getSUCCESS()) {
                            long albumId=message.getObject();
                            int code = DepManagerApi.depImageUpLoad(file,albumId);
                            subscriber.onNext(code);
                        }else {
                            Log.i(TAG, "call: 上传失败");
                        }

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
                            Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_SHORT).show();
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
