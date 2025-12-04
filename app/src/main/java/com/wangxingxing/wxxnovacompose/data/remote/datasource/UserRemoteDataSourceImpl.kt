package com.wangxingxing.wxxnovacompose.data.remote.datasource

import com.wangxingxing.wxxnovacompose.data.remote.RetrofitClient
import com.wangxingxing.wxxnovacompose.data.remote.api.UserApi
import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2024-09-03 16:25:00
 * email : 1099420259@qq.com
 * description : 用户远程数据源实现
 */
@Singleton
class UserRemoteDataSourceImpl @Inject constructor() : UserRemoteDataSource {

    private val userApi: UserApi by lazy {
        RetrofitClient.createService(UserApi::class.java)
    }

    override suspend fun getUsers(): List<UserResponse> {
        return userApi.getUsers()
    }

    override suspend fun getUserById(id: Int): UserResponse {
        return userApi.getUserById(id)
    }
}
