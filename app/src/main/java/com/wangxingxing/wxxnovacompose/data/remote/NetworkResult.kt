package com.wangxingxing.wxxnovacompose.data.remote

/**
 * author : 王星星
 * date : 2024-09-03 16:05:00
 * email : 1099420259@qq.com
 * description : 网络请求结果密封类
 */
sealed class NetworkResult<out T> {
    /**
     * 加载中
     */
    object Loading : NetworkResult<Nothing>()

    /**
     * 请求成功
     */
    data class Success<out T>(val data: T) : NetworkResult<T>()

    /**
     * 请求失败
     */
    data class Error(val error: Throwable, val message: String = error.message ?: "Unknown error") : NetworkResult<Nothing>()
}
