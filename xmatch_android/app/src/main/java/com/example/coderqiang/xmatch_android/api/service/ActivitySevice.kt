package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.BaseMessage
import com.example.coderqiang.xmatch_android.dto.MemberDto
import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.Activity
import com.example.coderqiang.xmatch_android.model.DepartmentAlbum
import com.example.coderqiang.xmatch_android.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by coderqiang on 2017/11/14.
 */

interface ActivitySevice{

    @GET("api/activity/getAll")
    fun getAllActivities(): Call<ObjectMessage<List<Activity>>>

    @GET("api/activity/get")
    fun getDepActivities(@Query("type")type:Int,
                         @Query("activityId")activityId: Long,
                         @Query("departmentId")departmentId: Long): Call<ObjectMessage<List<Activity>>>

    @GET("api/activity/get")
    fun getActivity(@Query("activityId")activityId:Long,
                    @Query("departmentId")departmentId:Long,
                    @Query("type")type:Long): Call<ObjectMessage<List<Activity>>>

    @GET("api/activity/delete")
    fun deleteActivity(@Query("activityId")activityId:Long): Call<BaseMessage>

    @POST("/api/album/image/add")
    fun addAlbum(@Body departmentAlbum: DepartmentAlbum):Call<ObjectMessage<Long>>

    @GET("/api/album/image/delete")
    fun deleteAlbum(@Query("albumId")activityId:Long): Call<BaseMessage>

}

