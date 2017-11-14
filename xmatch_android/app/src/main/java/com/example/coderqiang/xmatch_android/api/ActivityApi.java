package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.ActivitySevice;
import com.example.coderqiang.xmatch_android.api.service.DepManagerService;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.DepManagerLab;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/11/14.
 */

public class ActivityApi {

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


}
