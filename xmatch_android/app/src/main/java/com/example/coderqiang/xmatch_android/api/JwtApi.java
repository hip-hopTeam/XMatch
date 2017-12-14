package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.JwtService;
import com.example.coderqiang.xmatch_android.dto.JwtCourseDto;
import com.example.coderqiang.xmatch_android.dto.JwtLoginDto;
import com.example.coderqiang.xmatch_android.dto.JwtStudentDto;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.jwt.UtilDes;
import com.example.coderqiang.xmatch_android.util.jwt.UtilString;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/12/15.
 */

public class JwtApi {

    public static JwtLoginDto loginDto(Context context,String user, String passwd, String dateStr) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.JWTBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JwtService service = retrofit.create(JwtService.class);
        Call<JwtLoginDto> call = service.loginStudent("stulogin", user, passwd,dateStr,"","iPhone 6 (A1549/A1586)");
        try {
            JwtLoginDto jwtLoginDto = call.execute().body();
            System.out.println("LoginResult:"+new Gson().toJson(jwtLoginDto));
            if (jwtLoginDto.getStatus() == 0) {
                String token = user + "_" + dateStr + "_" + UtilString.getSeed(user, dateStr);
                try {
                    token = UtilDes.encryptDES(token);
                    jwtLoginDto.setToken(token);
                    DefaultConfig.get(context).setToken(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return jwtLoginDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JwtStudentDto getPersonInfo(String xh, String token) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.JWTBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JwtService service = retrofit.create(JwtService.class);
        Call<JwtStudentDto> call = service.getStudentInfo("stuInfo", token,xh);
        try {
            JwtStudentDto jwtStudentDto = call.execute().body();
            return jwtStudentDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JwtCourseDto getCourses(String token, String xq) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.JWTBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JwtService service = retrofit.create(JwtService.class);
        Call<JwtCourseDto> call = service.getCourses("table", token, xq);
        try {
            JwtCourseDto jwtCourseDto = call.execute().body();
            return jwtCourseDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
