package com.example.coderqiang.xmatch_android.util;

import android.content.Context;

import com.example.coderqiang.xmatch_android.dto.UserDto;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class UserDtoLab {
    private static UserDtoLab userDtoLab;

    private Context context;
    private UserDto userDto;

    private UserDtoLab(Context context){
        this.context=context;
    }

    public static UserDtoLab get(Context context){
        if (userDtoLab == null) {
            userDtoLab = new UserDtoLab(context);
        }
        return userDtoLab;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }


}
