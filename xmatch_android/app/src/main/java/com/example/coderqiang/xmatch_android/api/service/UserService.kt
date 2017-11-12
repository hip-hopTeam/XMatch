package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.DepManager
import com.example.coderqiang.xmatch_android.model.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by coderqiang on 2017/11/11.
 */
interface UserService {

    @POST("api/user/login")
    fun userLogin(@Query("stuNo") depManagerAccount:String,
                  @Query("passwd") password:String): Call<ObjectMessage<User>>

//    @GET("api/user/get")
//    fun getDepManagerById(@Path("id")depManagerId:Long): Call<ObjectMessage<DepManager>>

}