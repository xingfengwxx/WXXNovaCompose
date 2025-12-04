package com.wangxingxing.wxxnovacompose.data.repository

import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2024-09-03 16:30:00
 * email : 1099420259@qq.com
 * description : 用户数据仓库接口
 */
interface UserRepository {

    /**
     * 获取用户列表（Flow版本）
     */
    fun getUsers(): Flow<List<UserResponse>>

    /**
     * 根据ID获取用户（Flow版本）
     */
    fun getUserById(id: Int): Flow<UserResponse>

    /**
     * 获取用户列表（挂起函数版本）
     */
    suspend fun getUsersSuspend(): List<UserResponse>

    /**
     * 根据ID获取用户（挂起函数版本）
     */
    suspend fun getUserByIdSuspend(id: Int): UserResponse
}
