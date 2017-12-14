package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.ActivitySevice;
import com.example.coderqiang.xmatch_android.api.service.DepManagerService;
import com.example.coderqiang.xmatch_android.api.service.UserService;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.DepMember;
import com.example.coderqiang.xmatch_android.model.DepartmentAlbum;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ActivityApi {

    public static IntResultMessage addActivity(Activity activity){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/activity/add")
                .addHeader("content-type","application/json;charset:utf-8")
                .put(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        new Gson().toJson(activity)
                )).build();
        try {
            okhttp3.Response response=client.newCall(request).execute();
            IntResultMessage message = new Gson().fromJson(response.body().string(), IntResultMessage.class);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int activityImageUpLoad(File file, long activityId) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file",activityId+".png", RequestBody.create(MEDIA_TYPE_PNG, file));
        builder.addFormDataPart("activityId", activityId+"");
        final MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/activity/image/add")//地址
                .post(requestBody)//添加请求体
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.isSuccessful()) {
                String result=response.body().string();
                BaseMessage message = new Gson().fromJson(result, BaseMessage.class);
                System.out.println(result);
                return message.code;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultCode.Companion.getERROR();
    }

    public static List<Activity> getAllActivity(Context context) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice service = retrofit.create(ActivitySevice.class);
        Call<ObjectMessage<List<Activity>>> call = service.getAllActivities();
        try {
            ObjectMessage<List<Activity>> message = call.execute().body();
            return message.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> getDepActivity(long depId) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice service = retrofit.create(ActivitySevice.class);
        Call<ObjectMessage<List<Activity>>> call = service.getDepActivities(1,-1,depId);
        try {
            ObjectMessage<List<Activity>> message = call.execute().body();
            return message.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Activity getActivityDetail(Context context,long activityId) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice service = retrofit.create(ActivitySevice.class);
        Call<ObjectMessage<List<Activity>>> call = service.getActivity(activityId,-1,-1);
        try {
            ObjectMessage<List<Activity>> message = call.execute().body();
            return message.getObject().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int deleteActivity(Context context,long activityId) {
        System.out.println("activityId:" + activityId);
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice service = retrofit.create(ActivitySevice.class);
        Call<BaseMessage> call = service.deleteActivity(activityId);
        try {
            BaseMessage message = call.execute().body();
            return message.code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static ObjectMessage<Long> addAlbum(Context context,DepartmentAlbum departmentAlbum) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice activitySevice = retrofit.create(ActivitySevice.class);
        Call<ObjectMessage<Long>> userCall = activitySevice.addAlbum(departmentAlbum);
        try {
            ObjectMessage<Long> message= userCall.execute().body();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static int deleteAlbum(Context context,long albumId) {
        System.out.println("albumId:" + albumId);
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivitySevice service = retrofit.create(ActivitySevice.class);
        Call<BaseMessage> call = service.deleteAlbum(albumId);
        try {
            BaseMessage message = call.execute().body();
            return message.code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
