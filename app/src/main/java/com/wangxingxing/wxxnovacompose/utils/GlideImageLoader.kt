package com.wangxingxing.wxxnovacompose.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wangxingxing.wxxnovacompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * author : 王星星
 * date : 2024-09-03 17:00:00
 * email : 1099420259@qq.com
 * description : Glide图片加载工具类，提供Compose友好的图片加载接口
 */
object GlideImageLoader {

    /**
     * 加载网络图片（Compose版本）
     */
    @Composable
    fun LoadImage(
        url: String,
        modifier: Modifier = Modifier,
        contentScale: ContentScale = ContentScale.Crop,
        alignment: Alignment = Alignment.Center,
        placeholderResId: Int = R.mipmap.ic_launcher_round
    ) {
        val bitmapState = remember { mutableStateOf<android.graphics.Bitmap?>(null) }
        val context = LocalContext.current

        LaunchedEffect(url) {
            bitmapState.value = loadBitmapFromUrl(context, url)
        }

        val bitmap = bitmapState.value
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = modifier,
                contentScale = contentScale,
                alignment = alignment
            )
        } else {
            // 显示占位图
            Image(
                painter = painterResource(id = placeholderResId),
                contentDescription = null,
                modifier = modifier,
                contentScale = contentScale,
                alignment = alignment
            )
        }
    }

    /**
     * 从URL加载Bitmap
     */
    private suspend fun loadBitmapFromUrl(context: android.content.Context, url: String): android.graphics.Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(
                        RequestOptions()
                            .centerCrop()
                    )
                    .submit()
                    .get()
            } catch (e: Exception) {
                LogUtils.e("Image loading failed: ${e.message}", e)
                null
            }
        }
    }
}
