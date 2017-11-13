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
        val DEP_NOT_EXIST=14
        val DEP_MEMBER_EXIST=15
        val DEP_MEMBER_NOT_EXIST=16
        val CHILD_DEP_EXIST=17
        val CHILD_DEP_NOT_EXIST=18

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error",
                USER_EXIST to  "学号已存在",
                USER_NOT_EXIST to "用户不存在",
                USER_PASSWORD_ERROR to "密码错误",
                DEP_NOT_EXIST to "部门不存在",
                DEP_MEMBER_EXIST to "部员已存在",
                DEP_MEMBER_NOT_EXIST to "部员不存在",
                CHILD_DEP_EXIST to "子部门已存在",
                CHILD_DEP_NOT_EXIST to "子部门不存在"
        )
    }
}
