package com.wangxingxing.wxxnovacompose.core.ext

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.StringUtils

/**
 * author : 王星星
 * date : 2025/12/5 17:28
 * email : 1099420259@qq.com
 * description :
 */

fun Int.getString(): String {
    return StringUtils.getString(this)
}

/**
 * Int扩展函数，用于获取字符串资源
 */
fun Int.getString(context: Context): String {
    return context.getString(this)
}

/**
 * Int扩展函数，用于获取字符串资源（带格式化参数）
 */
fun Int.getString(context: Context, vararg formatArgs: Any): String {
    return context.getString(this, *formatArgs)
}

/**
 * Int扩展函数，通过Resources获取字符串资源
 */
fun Int.getString(resources: Resources): String {
    return resources.getString(this)
}

/**
 * Int扩展函数，通过Resources获取字符串资源（带格式化参数）
 */
fun Int.getString(resources: Resources, vararg formatArgs: Any): String {
    return resources.getString(this, *formatArgs)
}
