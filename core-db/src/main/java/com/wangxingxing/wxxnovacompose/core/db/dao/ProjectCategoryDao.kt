package com.wangxingxing.wxxnovacompose.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wangxingxing.wxxnovacompose.core.db.entity.ProjectCategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 项目分类 DAO 接口
 */
@Dao
interface ProjectCategoryDao {
    
    /**
     * 插入数据（如果已存在则替换）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: ProjectCategoryEntity)
    
    /**
     * 批量插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<ProjectCategoryEntity>)
    
    /**
     * 查询所有数据（Flow 方式，支持响应式）
     */
    @Query("SELECT * FROM project_category_table ORDER BY `order` ASC")
    fun getAll(): Flow<List<ProjectCategoryEntity>>
    
    /**
     * 查询所有数据（suspend 方式）
     */
    @Query("SELECT * FROM project_category_table ORDER BY `order` ASC")
    suspend fun getAllSync(): List<ProjectCategoryEntity>
    
    /**
     * 根据 ID 查询
     */
    @Query("SELECT * FROM project_category_table WHERE id = :id")
    suspend fun getById(id: Int): ProjectCategoryEntity?
    
    /**
     * 删除所有数据
     */
    @Query("DELETE FROM project_category_table")
    suspend fun deleteAll()
    
    /**
     * 检查是否有数据
     */
    @Query("SELECT COUNT(*) FROM project_category_table")
    suspend fun getCount(): Int
}