package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.BaseMessage
import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.DepManager
import com.example.coderqiang.xmatch_android.model.DepMember
import com.example.coderqiang.xmatch_android.model.Department
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

    @POST("api/user/add")
    fun addUser(@Body user:User):Call<ObjectMessage<Integer>>

    @POST("api/user/getDeps")
    fun getUserDeps(@Query ("userId") userId:Long):Call<ObjectMessage<List<Department>>>

    @POST("/api/depMemberManage/add")
    fun addDepApply(@Body depMember: DepMember):Call<BaseMessage>

//    @GET("api/user/get")
//    fun getDepManagerById(@Path("id")depManagerId:Long): Call<ObjectMessage<DepManager>>

}