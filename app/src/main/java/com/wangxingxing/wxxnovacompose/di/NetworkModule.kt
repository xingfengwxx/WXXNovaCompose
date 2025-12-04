package com.wangxingxing.wxxnovacompose.di

import com.wangxingxing.wxxnovacompose.data.remote.RetrofitClient
import com.wangxingxing.wxxnovacompose.data.remote.api.UserApi
import com.wangxingxing.wxxnovacompose.data.remote.datasource.UserRemoteDataSource
import com.wangxingxing.wxxnovacompose.data.remote.datasource.UserRemoteDataSourceImpl
import com.wangxingxing.wxxnovacompose.data.repository.UserRepository
import com.wangxingxing.wxxnovacompose.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2024-09-03 16:45:00
 * email : 1099420259@qq.com
 * description : 网络模块依赖注入配置
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    /**
     * 提供UserApi实例
     */
    companion object {
        @Provides
        @Singleton
        fun provideUserApi(): UserApi {
            return RetrofitClient.createService(UserApi::class.java)
        }

        /**
         * 提供UserRepository实现
         */
        @Provides
        @Singleton
        fun provideUserRepository(
            userRemoteDataSource: UserRemoteDataSource
        ): UserRepository {
            return UserRepositoryImpl(userRemoteDataSource)
        }
    }

    /**
     * 提供UserRemoteDataSource实现
     */
    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(
        impl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}
