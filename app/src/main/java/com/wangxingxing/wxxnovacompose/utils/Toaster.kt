package com.wangxingxing.wxxnovacompose.utils

import android.widget.Toast
import com.wangxingxing.wxxnovacompose.App

/**
 * author : 王星星
 * date : 2024-09-03 16:55:00
 * email : 1099420259@qq.com
 * description : Toast工具类，统一处理Toast消息显示
 */
object Toaster {

    /**
     * 显示短时间Toast消息
     */
    fun showShort(message: String) {
        Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示长时间Toast消息
     */
    fun showLong(message: String) {
        Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
    }

    /**
     * 显示短时间Toast消息（通过资源ID）
     */
    fun showShort(resId: Int) {
        Toast.makeText(App.instance, resId, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示长时间Toast消息（通过资源ID）
     */
    fun showLong(resId: Int) {
        Toast.makeText(App.instance, resId, Toast.LENGTH_LONG).show()
    }
}
