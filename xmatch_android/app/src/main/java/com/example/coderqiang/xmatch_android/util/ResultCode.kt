package com.example.coderqiang.xmatch_android.util

/**
 * Created by coderqiang on 2017/11/11.
 */
class ResultCode {

    companion object {
        val NET_ERROR=-1;
        val SUCCESS = 1
        val ERROR = 0

        val LOGIN_PWD_ERROR = 2
        val LOGIN_VERIFY_ERROR = 3
        val GET_BEGINDATE_ERROR = 10

        val USER_EXIST=11
        val USER_NOT_EXIST=12
        val USER_PASS_ERROR=13

        val DEP_MEMBER_EXIST=15

        val DEPMANAGER_NOT_EXIST =32
        val DEPMANAGER_PASSWORD_ERROR = 33

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error",
                USER_EXIST to  "学号已存在",
                USER_NOT_EXIST to "用户不存在",
                LOGIN_PWD_ERROR to "教务处密码错误",
                DEPMANAGER_NOT_EXIST to "部门管理员不存在",
                DEPMANAGER_PASSWORD_ERROR to "部门管理员登录密码错误",
                USER_PASS_ERROR to "密码错误",
                DEP_MEMBER_EXIST to "已加入该部门"
        )
    }
}