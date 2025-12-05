package com.wangxingxing.wxxnovacompose.feature.home.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.wangxingxing.wxxnovacompose.core.base.BaseViewModel
import com.wangxingxing.wxxnovacompose.core.base.UiState
import com.wangxingxing.wxxnovacompose.core.ext.getString
import com.wangxingxing.wxxnovacompose.core.models.remote.Banner
import com.wangxingxing.wxxnovacompose.core.network.ApiResult
import com.wangxingxing.wxxnovacompose.core.network.datasource.ArticlePagingSourceFactory
import com.wangxingxing.wxxnovacompose.feature.home.data.repository.BannerRepository
import com.wangxingxing.wxxnovacompose.ui.common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 首页 ViewModel
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val bannerRepository: BannerRepository,
    private val articlePagingSourceFactory: ArticlePagingSourceFactory
) : BaseViewModel(application) {

    override val uiState = MutableStateFlow<UiState>(UiState.Idle)

    /**
     * Banner 列表
     */
    val banners = MutableStateFlow<List<Banner>>(emptyList())

    /**
     * 文章列表 Paging Flow
     */
    val articles = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { articlePagingSourceFactory.create() }
    ).flow.cachedIn(viewModelScope)

    init {
        loadBannerData()
    }

    /**
     * 加载 Banner 数据
     */
    fun loadBannerData() {
        viewModelScope.launch {
            updateUiState(UiState.Loading)
            try {
                val result = bannerRepository.getBannerList()
                when (result) {
                    is ApiResult.Success -> {
                        banners.value = result.data
                        updateUiState(UiState.Success(result.data))
                    }
                    is ApiResult.Error -> {
                        updateUiState(UiState.Error(result.message))
                    }
                    else -> {
                        updateUiState(UiState.Error(R.string.error_unknown.getString()))
                    }
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    /**
     * 刷新 Banner 数据
     */
    fun refreshBanner() {
        loadBannerData()
    }
}

