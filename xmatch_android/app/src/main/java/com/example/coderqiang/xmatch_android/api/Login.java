package com.example.coderqiang.xmatch_android.api;

import com.example.coderqiang.xmatch_android.api.service.DepManagerService;
import com.example.coderqiang.xmatch_android.api.service.UserService;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.google.gson.Gson;

import java.io.IOException;

import javax.xml.transform.Result;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/11/11.
 */

public class Login {

    public static Integer loginUser(User user) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<ObjectMessage<User>> call= userService.userLogin(user.getStuNo(), user.getPasswd());
        try {
            ObjectMessage<User> message = call.execute().body();
            return message.getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultCode.Companion.getERROR();
    }
}
