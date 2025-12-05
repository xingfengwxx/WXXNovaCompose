package com.wangxingxing.wxxnovacompose.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2024-09-03 14:30:00
 * email : 1099420259@qq.com
 * description : Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        observeData()
    }

    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 观察数据变化
     */
    open fun observeData() {
        // 子类可重写实现数据观察
    }

    /**
     * 安全地启动协程，在指定生命周期内执行
     */
    protected fun launchWhenStarted(block: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                block()
            }
        }
    }

    /**
     * 安全地启动协程，在指定生命周期内执行
     */
    protected fun launchWhenResumed(block: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                block()
            }
        }
    }
}