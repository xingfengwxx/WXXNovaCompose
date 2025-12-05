package com.wangxingxing.wxxnovacompose.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wangxingxing.wxxnovacompose.core.db.dao.DemoDao
import com.wangxingxing.wxxnovacompose.core.db.dao.ProjectCategoryDao
import com.wangxingxing.wxxnovacompose.core.db.entity.DemoEntity
import com.wangxingxing.wxxnovacompose.core.db.entity.ProjectCategoryEntity

/**
 * author : 王星星
 * date : 2025/11/20 19:26
 * email : 1099420259@qq.com
 * description : Room 数据库
 */
@Database(
    entities = [DemoEntity::class, ProjectCategoryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * 获取 DemoDao
     */
    abstract fun demoDao(): DemoDao
    
    /**
     * 获取 ProjectCategoryDao
     */
    abstract fun projectCategoryDao(): ProjectCategoryDao
}