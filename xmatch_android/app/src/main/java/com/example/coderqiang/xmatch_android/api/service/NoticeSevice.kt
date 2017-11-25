package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.BaseMessage
import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.Activity
import com.example.coderqiang.xmatch_android.model.AppNotice
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by coderqiang on 2017/11/14.
 */

interface NoticeSevice {

    @GET("api/notice/manager/get_all")
    fun getAllNotices(): Call<ObjectMessage<List<AppNotice>>>

    @GET("api/notice/manager/get_department")
    fun getDepNotices(@Query("type")type:Int,
                         @Query("departmentId")activityId: Long): Call<ObjectMessage<List<AppNotice>>>

    @GET("api/notice/manager/delete")
    fun deleteActivity(@Query("appNoticeId")activityId:Long): Call<BaseMessage>

}