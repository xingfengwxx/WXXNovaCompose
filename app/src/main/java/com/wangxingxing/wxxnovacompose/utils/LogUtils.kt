package com.wangxingxing.wxxnovacompose.utils

import android.util.Log
import com.wangxingxing.wxxnovacompose.BuildConfig

/**
 * author : 王星星
 * date : 2024-09-03 16:50:00
 * email : 1099420259@qq.com
 * description : 日志工具类，统一处理日志打印
 */
object LogUtils {

    private const val TAG = "WXXNovaCompose"
    private val isDebug = BuildConfig.DEBUG

    /**
     * 打印INFO级别日志
     */
    fun i(message: String, tag: String = TAG) {
        if (isDebug) {
            Log.i(tag, message)
        }
    }

    /**
     * 打印DEBUG级别日志
     */
    fun d(message: String, tag: String = TAG) {
        if (isDebug) {
            Log.d(tag, message)
        }
    }

    /**
     * 打印ERROR级别日志
     */
    fun e(message: String, throwable: Throwable? = null, tag: String = TAG) {
        if (isDebug) {
            if (throwable != null) {
                Log.e(tag, message, throwable)
            } else {
                Log.e(tag, message)
            }
        }
    }

    /**
     * 打印VERBOSE级别日志
     */
    fun v(message: String, tag: String = TAG) {
        if (isDebug) {
            Log.v(tag, message)
        }
    }

    /**
     * 打印WARN级别日志
     */
    fun w(message: String, tag: String = TAG) {
        if (isDebug) {
            Log.w(tag, message)
        }
    }
}
