package com.wangxingxing.wxxnovacompose.feature.home.data.repository

import android.content.Context
import com.wangxingxing.wxxnovacompose.core.network.ApiResult
import com.wangxingxing.wxxnovacompose.core.network.api.WanAndroidApiService
import com.wangxingxing.wxxnovacompose.core.models.remote.Banner
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : Banner Repository，负责 Banner 数据获取
 */
@Singleton
class BannerRepository @Inject constructor(
    private val wanAndroidApiService: WanAndroidApiService,
    @ApplicationContext private val context: Context
) {
    
    /**
     * 获取 Banner 列表
     */
    suspend fun getBannerList(): ApiResult<List<Banner>> {
        return try {
            val response = wanAndroidApiService.getBannerList()
            response.toApiResult()
        } catch (e: Exception) {
            ApiResult.Error(-1, e.message ?: "网络请求失败")
        }
    }
}