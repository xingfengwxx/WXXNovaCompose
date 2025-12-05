package com.wangxingxing.wxxnovacompose.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.wxxnovacompose.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2024-09-03 14:40:00
 * email : 1099420259@qq.com
 * description : ViewModel基类
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * UI 状态
     */
    protected abstract val uiState: MutableStateFlow<UiState>

    /**
     * 获取 UI 状态（只读）
     */
    fun getUiState(): StateFlow<UiState> = uiState.asStateFlow()

    /**
     * 更新 UI 状态
     */
    protected fun updateUiState(state: UiState) {
        viewModelScope.launch {
            uiState.value = state
        }
    }

    /**
     * 默认的异常处理器
     */
    protected val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // 这里可以统一处理异常，比如日志记录
        throwable.printStackTrace()
        onError(throwable)
    }

    /**
     * 在ViewModel的作用域内启动协程
     */
    protected fun launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch(defaultExceptionHandler) {
            try {
                block()
            } catch (e: Exception) {
                onError?.invoke(e)
                this@BaseViewModel.onError(e)
                handleError(e)
            }
        }
    }

    /**
     * 错误处理回调
     */
    open fun onError(throwable: Throwable) {
        // 子类可重写处理错误
    }

    /**
     * 错误处理
     */
    protected open fun handleError(e: Exception) {
        val errorMessage = e.message ?: getApplication<Application>().getString(
            R.string.error_unknown
        )
        updateUiState(UiState.Error(errorMessage))
    }
}

/**
 * UI 状态密封类
 */
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String) : UiState()
}
