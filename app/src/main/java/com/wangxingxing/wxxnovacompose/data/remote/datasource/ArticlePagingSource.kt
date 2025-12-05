package com.wangxingxing.wxxnovacompose.data.remote.datasource

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.wxxnovacompose.data.remote.api.WanAndroidApiService
import com.wangxingxing.wxxnovacompose.data.remote.model.Article

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 文章列表 PagingSource，用于 Paging3 分页加载
 */
class ArticlePagingSource(
    private val wanAndroidApiService: WanAndroidApiService,
    private val context: Context
) : PagingSource<Int, Article>() {

    companion object {
        private const val PAGE_SIZE = 20 // 每页数量
        private const val STARTING_PAGE_INDEX = 0 // 起始页码
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            // 获取要加载的页码，如果为 null 则从起始页开始
            val page = params.key ?: STARTING_PAGE_INDEX
            
            // 调用接口获取数据
            val response = wanAndroidApiService.getArticleList(
                page = page,
                pageSize = PAGE_SIZE
            )
            
            if (response.isSuccess() && response.data != null) {
                val articles = response.data.datas
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (response.data.over) null else page + 1
                
                LoadResult.Page(
                    data = articles,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(
                    Exception(response.message.ifEmpty { 
                        context.getString(com.wangxingxing.wxxnovacompose.R.string.error_network_request_failed)
                    })
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // 当需要刷新时，返回当前页的 key
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

