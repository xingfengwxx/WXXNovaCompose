package com.wangxingxing.wxxnovacompose.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * author : 王星星
 * date : 2024-09-03 15:50:00
 * email : 1099420259@qq.com
 * description : Retrofit客户端配置
 */
object RetrofitClient {

    // 创建Moshi实例
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // 创建Retrofit实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .client(OkHttpConfig.createOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * 创建API服务
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
