package com.wangxingxing.wxxnovacompose

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.hjq.toast.Toaster
import dagger.hilt.android.HiltAndroidApp

/**
 * author : 王星星
 * date : 2024-09-03 15:30:00
 * email : 1099420259@qq.com
 * description : 应用类，用于初始化Hilt
 */
@HiltAndroidApp
class App : Application() {

    companion object {
        const val TAG = "wxx"

        lateinit var instance: App
            private set

        fun isDebug() = BuildConfig.DEBUG
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Utils.init(instance)
        // 初始化日志配置
        LogUtils.getConfig()
            .setLogSwitch(isDebug())
            .setGlobalTag(TAG)
            .setBorderSwitch(true)

        Toaster.init(instance)
    }
}