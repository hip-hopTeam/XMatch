package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.ActivitySevice;
import com.example.coderqiang.xmatch_android.api.service.DepManagerService;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
            System.out.println("code:"+message.getObject().size());
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


}
