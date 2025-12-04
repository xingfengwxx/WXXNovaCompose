package com.wangxingxing.wxxnovacompose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2024-09-03 14:40:00
 * email : 1099420259@qq.com
 * description : ViewModel基类
 */
open class BaseViewModel : ViewModel() {

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
            }
        }
    }

    /**
     * 错误处理回调
     */
    open fun onError(throwable: Throwable) {
        // 子类可重写处理错误
    }
}