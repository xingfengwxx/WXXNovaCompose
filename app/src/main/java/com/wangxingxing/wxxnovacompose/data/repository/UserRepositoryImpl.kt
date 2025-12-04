package com.wangxingxing.wxxnovacompose.data.repository

import com.wangxingxing.wxxnovacompose.data.remote.datasource.UserRemoteDataSource
import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2024-09-03 16:35:00
 * email : 1099420259@qq.com
 * description : 用户数据仓库实现
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUsers(): Flow<List<UserResponse>> {
        return flow {
            emit(userRemoteDataSource.getUsers())
        }.flowOn(ioDispatcher)
    }

    override fun getUserById(id: Int): Flow<UserResponse> {
        return flow {
            emit(userRemoteDataSource.getUserById(id))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getUsersSuspend(): List<UserResponse> {
        return userRemoteDataSource.getUsers()
    }

    override suspend fun getUserByIdSuspend(id: Int): UserResponse {
        return userRemoteDataSource.getUserById(id)
    }
}
