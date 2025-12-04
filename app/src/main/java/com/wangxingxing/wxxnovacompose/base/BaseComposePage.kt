package com.wangxingxing.wxxnovacompose.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * author : 王星星
 * date : 2024-09-03 14:45:00
 * email : 1099420259@qq.com
 * description : Compose页面基类
 */
abstract class BaseComposePage {
    @Composable
    abstract fun Content()

    @Composable
    fun ComposePage() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Content()
        }
    }
}

/**
 * 默认预览函数，所有Compose页面都应该使用此函数进行预览
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}