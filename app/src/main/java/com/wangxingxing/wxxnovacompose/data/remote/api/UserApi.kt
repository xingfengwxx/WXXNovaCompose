package com.wangxingxing.wxxnovacompose.data.remote.api

import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author : 王星星
 * date : 2024-09-03 15:55:00
 * email : 1099420259@qq.com
 * description : 用户API接口
 */
interface UserApi {

    /**
     * 获取用户列表
     */
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    /**
     * 根据ID获取用户
     */
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserResponse
}
