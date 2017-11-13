package com.example.coderqiang.xmatch_android.api;

import android.content.Context;
import android.util.Log;

import com.example.coderqiang.xmatch_android.api.service.DepManagerService;
import com.example.coderqiang.xmatch_android.api.service.UserService;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.ChildDepartmentListMessage;
import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.dto.DepManagerMessage;
import com.example.coderqiang.xmatch_android.dto.DepartmentDto;
import com.example.coderqiang.xmatch_android.dto.DepartmentListMessage;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.model.ChildDepartment;
import com.example.coderqiang.xmatch_android.model.DepManager;
import com.example.coderqiang.xmatch_android.model.DepMember;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepManagerApi {
    private static final String TAG="DepManagerApi";

    public static DepManagerDto getDepmanager(long depManagerId) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/depManager/get?depManagerId="+depManagerId)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result=response.body().string();
                DepManagerMessage objectMessage=new Gson().fromJson(result,DepManagerMessage.class);
                return objectMessage.getObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Department getDepartment(long departmentId) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/department/get?departmentId="+departmentId)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMessage<Department> objectMessage=new Gson().fromJson(response.body().string(),ObjectMessage.class);
                return objectMessage.getObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BaseMessage addChildDepartment(ChildDepartment childDepartment){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/department/addChildDep")
                .addHeader("content-type","application/json;charset:utf-8")
                .put(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        new Gson().toJson(childDepartment)
                )).build();
        try {
            okhttp3.Response response=client.newCall(request).execute();
            BaseMessage message = new Gson().fromJson(response.body().string(), BaseMessage.class);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ChildDepartment> getChildDepartments(long departmentId) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/department/getChildDep?depId="+departmentId)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()) {
               ChildDepartmentListMessage message=new Gson().fromJson(response.body().string(),ChildDepartmentListMessage.class);
                return message.getObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Department> getAllDepartments(){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/department/findAll")
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                DepartmentListMessage departmentListMessage=new Gson().fromJson(response.body().string(),DepartmentListMessage.class);
                return departmentListMessage.getObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<MemberDto> getMembersByState(Context context) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DepManagerService service = retrofit.create(DepManagerService.class);
        Call<ObjectMessage<List<MemberDto>>> call= service.getMemberByState(DefaultConfig.get(context).getDepartmentId());
        try {
            ObjectMessage<List<MemberDto>> message = call.execute().body();
            return message.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String updateMemberState(DepMember member,Context context) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/depMemberManage/update")
                .addHeader("content-type","application/json;charset:utf-8")
                .put(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        new Gson().toJson(member)
                )).build();
        Log.i(TAG, "updateMemberState: "+new Gson().toJson(member));
        try {
            okhttp3.Response response=client.newCall(request).execute();
            Log.i(TAG, "updateMemberState: "+response.code());
            BaseMessage message = new Gson().fromJson(response.body().string(), BaseMessage.class);
            return message.result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
