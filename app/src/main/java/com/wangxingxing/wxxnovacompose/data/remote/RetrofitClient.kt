package com.wangxingxing.wxxnovacompose.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : 王星星
 * date : 2024-09-03 15:50:00
 * email : 1099420259@qq.com
 * description : Retrofit客户端配置
 */
object RetrofitClient {

    // 创建Gson实例
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    // 创建Retrofit实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .client(OkHttpConfig.createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /**
     * 创建API服务
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
