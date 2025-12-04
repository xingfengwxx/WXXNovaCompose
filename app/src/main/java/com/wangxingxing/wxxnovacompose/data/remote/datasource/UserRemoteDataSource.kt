package com.wangxingxing.wxxnovacompose.data.remote.datasource

import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse

/**
 * author : 王星星
 * date : 2024-09-03 16:20:00
 * email : 1099420259@qq.com
 * description : 用户远程数据源接口
 */
interface UserRemoteDataSource {

    /**
     * 获取用户列表
     */
    suspend fun getUsers(): List<UserResponse>

    /**
     * 根据ID获取用户
     */
    suspend fun getUserById(id: Int): UserResponse
}
