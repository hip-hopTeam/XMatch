package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.ActivitySevice;
import com.example.coderqiang.xmatch_android.api.service.NoticeSevice;
import com.example.coderqiang.xmatch_android.dto.IntResultMessage;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.Activity;
import com.example.coderqiang.xmatch_android.model.AppNotice;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
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
 * Created by coderqiang on 2017/11/24.
 */

public class NoticeApi {

    public static IntResultMessage addNotice(AppNotice notice){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/notice/manager/add")
                .addHeader("content-type","application/json;charset:utf-8")
                .put(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        new Gson().toJson(notice)
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

    public static List<AppNotice> getNotices(int type,long depId) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NoticeSevice service = retrofit.create(NoticeSevice.class);
        System.out.println("type:" + type + ",depId:" + depId);
        Call<ObjectMessage<List<AppNotice>>> call=null;
        if (type == 1) {
            call = service.getDepNotices(type, depId);
        }else if (type==2){
            call = service.getAllNotices();
        }else {
            return null;
        }
        try {
            ObjectMessage<List<AppNotice>> message = call.execute().body();
            return message.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<AppNotice> getUserNotices(long userId) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NoticeSevice service = retrofit.create(NoticeSevice.class);
        Call<ObjectMessage<List<AppNotice>>> call= service.getUserAllNotices(userId);

        try {
            ObjectMessage<List<AppNotice>> message = call.execute().body();
            return message.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
