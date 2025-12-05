package com.wangxingxing.wxxnovacompose.core.models.remote

import com.google.gson.annotations.SerializedName

/**
 * 文章分页数据模型
 */
data class ArticlePageData(
    @SerializedName("curPage")
    val curPage: Int,
    @SerializedName("datas")
    val datas: List<Article>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("over")
    val over: Boolean,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
)