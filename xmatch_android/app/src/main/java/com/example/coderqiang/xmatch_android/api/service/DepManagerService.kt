package com.example.coderqiang.xmatch_android.api.service

import com.example.coderqiang.xmatch_android.dto.BaseMessage
import com.example.coderqiang.xmatch_android.dto.DepManagerDto
import com.example.coderqiang.xmatch_android.dto.MemberDto
import com.example.coderqiang.xmatch_android.dto.ObjectMessage
import com.example.coderqiang.xmatch_android.model.DepManager
import com.example.coderqiang.xmatch_android.model.Department
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by coderqiang on 2017/11/11.
 */
interface DepManagerService {

    @POST("api/depManager/login")
    fun depmanagerLogin(@Query("depManagerAccount") depManagerAccount:String,
                        @Query("password") password:String): Call<ObjectMessage<DepManagerDto>>

    @GET("api/depManager/get/{id}")
    fun getDepManagerById(@Path("id")depManagerId:Long): Call<ObjectMessage<DepManager>>



    @GET("api/department/findAll")
    fun getAllDepartment():Call<ObjectMessage<Department>>

    @GET("api/depMemberManage/get")
    fun getMemberByState(@Query("depId")depManagerId: Long): Call<ObjectMessage<List<MemberDto>>>

    @POST("api/depMemberManage/update")
    fun updateMember(@Body requestBody: RequestBody): Call<BaseMessage>

    @GET("api/department/deleteChildDep")
    fun deleteChildDepartment(@Query("childDepartmentId")childDepartmentId: Long):Call<BaseMessage>


}