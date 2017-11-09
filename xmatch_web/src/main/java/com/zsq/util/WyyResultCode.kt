package com.zsq.util

/**
 * Created by Administrator on 2017/11/9/009.
 */
class WyyResultCode {
    companion object {
        val SUCCESS = 1
        val ERROR = 0
        val USER_EXIST=11
        val USER_NOT_EXIST=12
        val USER_PASSWORD_ERROR=13

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error",
                USER_EXIST to  "学号已存在",
                USER_NOT_EXIST to "用户不存在",
                USER_PASSWORD_ERROR to "密码错误"
        )
    }
}
