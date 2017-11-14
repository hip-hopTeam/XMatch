
package com.zsq.util

/**
 * Created by ThomasNg on 2017/11/10.
 */
class ThoResultCode {
    companion object {
        val SUCCESS = 1
        val ERROR = 0
        val NOTICE_EXIST=11
        val NOTICE_NOT_EXIST=12
        val TITLE_OR_CONTENT_IS_NULL=13

        val map = mapOf<Int, String>(
                SUCCESS to "success",
                ERROR to "error",
                NOTICE_EXIST to  "通知已存在",
                NOTICE_NOT_EXIST to "通知不存在",
                TITLE_OR_CONTENT_IS_NULL to "通知标题或内容不能为空"
        )
    }
}
