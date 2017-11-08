package com.zsq.util

/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/4.
 */
class ResultCode {
    companion object {
        val SUCCESS = 1
        val ERROR = 0
        val USER_EXIST=11
        val USER_NOT_EXIST=12

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error",
                USER_EXIST to  "学号已存在",
                USER_NOT_EXIST to "用户不存在"
        )
    }
}