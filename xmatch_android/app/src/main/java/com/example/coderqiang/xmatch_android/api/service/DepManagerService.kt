package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.DepManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by coderqiang on 2017/11/11.
 */
interface DepManagerService {

    @POST("api/depManager/login")
    fun depmanagerLogin(@Field("depManagerAccount") depManagerAccount:String,
                        @Field("password") password:String): Call<ObjectMessage<DepManager>>

    @GET("api/depManager/get/{id}")
    fun getDepManagerById(@Path("id")depManagerId:Long): Call<ObjectMessage<DepManager>>

}