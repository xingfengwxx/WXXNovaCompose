package com.wangxingxing.wxxnovacompose.data.repository

import android.content.Context
import com.wangxingxing.wxxnovacompose.R
import com.wangxingxing.wxxnovacompose.data.local.db.dao.ProjectCategoryDao
import com.wangxingxing.wxxnovacompose.data.local.db.entity.ProjectCategoryEntity
import com.wangxingxing.wxxnovacompose.data.remote.ApiResult
import com.wangxingxing.wxxnovacompose.data.remote.api.WanAndroidApiService
import com.wangxingxing.wxxnovacompose.data.remote.model.ProjectCategory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 项目分类 Repository，负责数据获取和存储
 */
@Singleton
class ProjectCategoryRepository @Inject constructor(
    private val projectCategoryDao: ProjectCategoryDao,
    private val wanAndroidApiService: WanAndroidApiService,
    @ApplicationContext private val context: Context
) {
    
    /**
     * 获取项目分类数据（Flow 方式，支持响应式）
     * 如果本地没有数据，则从网络获取并存储
     */
    suspend fun getProjectCategories(): Flow<List<ProjectCategoryEntity>> {
        // 检查本地是否有数据
        val count = projectCategoryDao.getCount()
        if (count == 0) {
            // 本地没有数据，从网络获取
            fetchAndSaveProjectCategories()
        }
        // 返回本地数据
        return projectCategoryDao.getAll()
    }
    
    /**
     * 从网络获取项目分类数据并保存到本地
     */
    suspend fun fetchAndSaveProjectCategories(): ApiResult<List<ProjectCategoryEntity>> {
        return try {
            val response = wanAndroidApiService.getProjectTree()
            if (response.isSuccess() && response.data != null) {
                // 转换为 Entity 并保存到数据库
                val entities = response.data.map { it.toEntity() }
                projectCategoryDao.insertAll(entities)
                ApiResult.Success(entities)
            } else {
                ApiResult.Error(response.code, response.message)
            }
        } catch (e: Exception) {
            ApiResult.Error(-1, e.message ?: context.getString(R.string.error_network_request_failed))
        }
    }
    
    /**
     * 刷新数据（强制从网络获取）
     * 
     * 下拉刷新时，不管本地是否有数据都请求网络
     * 此方法会先清空本地数据，然后从网络获取最新数据并保存到本地
     */
    suspend fun refreshProjectCategories(): ApiResult<List<ProjectCategoryEntity>> {
        // 先清空本地数据
        projectCategoryDao.deleteAll()
        // 从网络获取新数据（不管本地是否有数据都请求网络）
        return fetchAndSaveProjectCategories()
    }
    
    /**
     * 检查本地是否有数据
     */
    suspend fun hasLocalData(): Boolean {
        return projectCategoryDao.getCount() > 0
    }
}

/**
 * 将 ProjectCategory 转换为 ProjectCategoryEntity
 */
private fun ProjectCategory.toEntity(): ProjectCategoryEntity {
    return ProjectCategoryEntity(
        id = id,
        name = name,
        courseId = courseId,
        parentChapterId = parentChapterId,
        order = order,
        visible = visible,
        type = type,
        desc = desc,
        cover = cover,
        author = author,
        lisense = lisense,
        lisenseLink = lisenseLink,
        userControlSetTop = userControlSetTop
    )
}

