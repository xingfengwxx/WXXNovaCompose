package com.wangxingxing.wxxnovacompose

import android.app.Application
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
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}