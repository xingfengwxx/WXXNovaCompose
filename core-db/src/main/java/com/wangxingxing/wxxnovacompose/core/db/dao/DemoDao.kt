package com.wangxingxing.wxxnovacompose.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wangxingxing.wxxnovacompose.core.db.entity.DemoEntity
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2025/11/20 19:26
 * email : 1099420259@qq.com
 * description : 示例 DAO 接口
 */
@Dao
interface DemoDao {
    
    /**
     * 插入数据
     */
    @Insert
    suspend fun insert(demo: DemoEntity): Long
    
    /**
     * 批量插入
     */
    @Insert
    suspend fun insertAll(demos: List<DemoEntity>)
    
    /**
     * 更新数据
     */
    @Update
    suspend fun update(demo: DemoEntity)
    
    /**
     * 根据 ID 查询
     */
    @Query("SELECT * FROM demo_table WHERE id = :id")
    suspend fun getById(id: Long): DemoEntity?
    
    /**
     * 查询所有数据（Flow 方式，支持响应式）
     */
    @Query("SELECT * FROM demo_table ORDER BY createTime DESC")
    fun getAll(): Flow<List<DemoEntity>>
    
    /**
     * 删除所有数据
     */
    @Query("DELETE FROM demo_table")
    suspend fun deleteAll()
    
    /**
     * 根据 ID 删除
     */
    @Query("DELETE FROM demo_table WHERE id = :id")
    suspend fun deleteById(id: Long)
}