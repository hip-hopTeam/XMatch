package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.MemberDto
import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.Activity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by coderqiang on 2017/11/14.
 */

interface ActivitySevice{

    @GET("api/activity/getAll")
    fun getAllActivities(): Call<ObjectMessage<List<Activity>>>

}

