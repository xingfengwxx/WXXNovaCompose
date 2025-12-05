package com.wangxingxing.wxxnovacompose.core.network.datasource

import android.content.Context
import com.wangxingxing.wxxnovacompose.core.network.api.WanAndroidApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : ArticlePagingSource 工厂类，用于创建 PagingSource 实例
 */
@Singleton
class ArticlePagingSourceFactory @Inject constructor(
    private val wanAndroidApiService: WanAndroidApiService,
    @ApplicationContext private val context: Context
) {
    fun create(): ArticlePagingSource {
        return ArticlePagingSource(wanAndroidApiService, context)
    }
}