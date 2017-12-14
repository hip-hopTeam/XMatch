package com.example.coderqiang.xmatch_android.api;

import android.content.Context;

import com.example.coderqiang.xmatch_android.api.service.UserService;
import com.example.coderqiang.xmatch_android.dto.BaseMessage;
import com.example.coderqiang.xmatch_android.dto.JwtLoginDto;
import com.example.coderqiang.xmatch_android.dto.ObjectMessage;
import com.example.coderqiang.xmatch_android.dto.UserDto;
import com.example.coderqiang.xmatch_android.dto.UserMessage;
import com.example.coderqiang.xmatch_android.model.DepMember;
import com.example.coderqiang.xmatch_android.model.Department;
import com.example.coderqiang.xmatch_android.model.User;
import com.example.coderqiang.xmatch_android.util.DefaultConfig;
import com.example.coderqiang.xmatch_android.util.ResultCode;
import com.example.coderqiang.xmatch_android.util.jwt.UtilTime;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class UserApi {

    public static Integer loginUser(Context context,User user) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<ObjectMessage<User>> call= userService.userLogin(user.getStuNo(), user.getPasswd());
        try {
            ObjectMessage<User> message = call.execute().body();
            System.out.println("服务器result:"+new Gson().toJson(message));
            if (message.code == 12) {//用户不存在
                System.out.println("用户不存在，去教务处验证");
                int res=loginByJwt(context,user);
                if (res==ResultCode.Companion.getSUCCESS()){
                    Call<ObjectMessage<Integer>> userCall=userService.addUser(user);
                    ObjectMessage<Integer> baseMessage=userCall.execute().body();
                    if (baseMessage.getCode() == ResultCode.Companion.getSUCCESS()) {
                        System.out.println("userId:"+baseMessage.getObject());
                        DefaultConfig.get(context).setUserId(baseMessage.getObject());
                    }
                    return baseMessage.getCode();
                }
                return res;
            }
            if (message.getObject()!=null)
                DefaultConfig.get(context).setUserId(message.getObject().getUserId());
            return message.code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultCode.Companion.getERROR();
    }


    public static int loginByJwt(Context context, User user){
        String dateStr= UtilTime.getCurrentTimeStr();
        JwtLoginDto jwtLoginDto = JwtApi.loginDto(context,user.getStuNo(),user.getPasswd(),dateStr);
        if (jwtLoginDto == null) {
            return ResultCode.Companion.getNET_ERROR();
        }
        if (jwtLoginDto.getStatus() == 0) {
            user.setUsername(jwtLoginDto.getStudent().getXm());
            user.setCollege(jwtLoginDto.getStudent().getXyhmc());
            user.setEmail(jwtLoginDto.getStudent().getEmail());
            user.setSummary(jwtLoginDto.getStudent().getNj()+"级"+jwtLoginDto.getStudent().getXyhmc()+jwtLoginDto.getStudent().getXb()+"生一枚~~~");
            if (jwtLoginDto.getStudent().getXb().equals("男")) {
                user.setSex(1);
            }else {
                user.setSex(2);
            }
            return ResultCode.Companion.getSUCCESS();
        }else if (jwtLoginDto.getStatus()==1){
            return ResultCode.Companion.getLOGIN_PWD_ERROR();
        }else {
            return ResultCode.Companion.getERROR();
        }

    }

    public static UserDto getUser(long userId) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/user/get?userId="+userId)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result=response.body().string();
                UserMessage objectMessage=new Gson().fromJson(result,UserMessage.class);
                return objectMessage.getObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addApplyToDepartment(Context context,DepMember depMember) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<BaseMessage> userCall = userService.addDepApply(depMember);
        try {
            BaseMessage baseMessage= userCall.execute().body();
            return baseMessage.getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Department> getUserDeps(long userId){
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DefaultConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<ObjectMessage<List<Department>>> call = userService.getUserDeps(userId);
        try {
            ObjectMessage<List<Department>> message=call.execute().body();
            if (message.getCode() == ResultCode.Companion.getSUCCESS()) {
                return message.getObject();
            }
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static int imageUpLoad(File file, long userId) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("avator",userId+".png", RequestBody.create(MEDIA_TYPE_PNG, file));
        builder.addFormDataPart("userId", userId+"");
        final MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url(DefaultConfig.BASE_URL+"/api/user/avator/add")//地址
                .post(requestBody)//添加请求体
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.isSuccessful()) {
                String result=response.body().string();
                BaseMessage message = new Gson().fromJson(result, BaseMessage.class);
                System.out.println(result);
                return message.code;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultCode.Companion.getERROR();
    }
}
