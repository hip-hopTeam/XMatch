package com.zsq.util

/**
 * Created by coderqiang on 2017/11/4.
 */
class ResultCode {
    companion object {
        val SUCCESS = 1
        val ERROR = 0

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error"
        )
    }
}