package com.example.coderqiang.xmatch_android.api.service;

import com.example.coderqiang.xmatch_android.dto.JwtCourseDto;
import com.example.coderqiang.xmatch_android.dto.JwtLoginDto;
import com.example.coderqiang.xmatch_android.dto.JwtStudentDto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by coderqiang on 2017/11/27.
 */

public interface JwtService {


    @FormUrlEncoded
    @POST("User"+"Hand"+"ler.a"+"shx")
    public Call<JwtLoginDto> loginStudent(@Field("met" + "hodT" + "ype") String methodType,
                                          @Field("x" + "h") String xuehao,
                                          @Field("pwd") String passwd,
                                          @Field("date") String dateStr,
                                          @Field("c" + "onn" + "ect") String connect,
                                          @Field("ma" + "chi" + "ne") String machine);

    @FormUrlEncoded
    @POST("Cour"+"seHa"+"ndler.a"+"shx")
    public Call<JwtCourseDto> getCourses(@Field("met" + "hodT" + "ype") String methodType,
                                         @Field("to" + "ken") String token,
                                         @Field("kkxq") String kkxq);


    @FormUrlEncoded
    @POST("User"+"Hand"+"ler.a"+"shx")
    public Call<JwtStudentDto> getStudentInfo(@Field("met" + "hodT" + "ype") String methodType,
                                              @Field("to" + "ken") String token,
                                              @Field("x" + "h") String kkxq);



}
