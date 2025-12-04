package com.wangxingxing.wxxnovacompose.data.remote

import android.content.Context
import com.wangxingxing.wxxnovacompose.App
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author : 王星星
 * date : 2024-09-03 15:45:00
 * email : 1099420259@qq.com
 * description : OkHttp客户端配置
 */
object OkHttpConfig {

    /**
     * 创建OkHttp客户端
     */
    fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // 缓存配置
        val cacheFile = File(App.instance.cacheDir, "http_cache")
        val cache = Cache(cacheFile, NetworkConfig.CACHE_SIZE)

        return OkHttpClient.Builder()
            .connectTimeout(NetworkConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConfig.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor())
            .cache(cache)
            .build()
    }

    /**
     * 头部拦截器，添加公共请求头
     */
    private fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}
