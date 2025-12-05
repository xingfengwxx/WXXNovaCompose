package com.wangxingxing.wxxnovacompose.core.di.database

import android.content.Context
import androidx.room.Room
import com.wangxingxing.wxxnovacompose.core.db.AppDatabase
import com.wangxingxing.wxxnovacompose.core.db.dao.DemoDao
import com.wangxingxing.wxxnovacompose.core.db.dao.ProjectCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2025/11/20 19:26
 * email : 1099420259@qq.com
 * description : 数据库模块配置
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDemoDao(database: AppDatabase): DemoDao {
        return database.demoDao()
    }

    @Provides
    @Singleton
    fun provideProjectCategoryDao(database: AppDatabase): ProjectCategoryDao {
        return database.projectCategoryDao()
    }
}