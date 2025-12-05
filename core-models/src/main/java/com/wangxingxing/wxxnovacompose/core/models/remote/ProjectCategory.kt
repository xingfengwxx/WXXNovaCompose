package com.wangxingxing.wxxnovacompose.core.models.remote

import com.google.gson.annotations.SerializedName

/**
 * 项目分类数据模型
 */
data class ProjectCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("courseId")
    val courseId: Int,
    @SerializedName("parentChapterId")
    val parentChapterId: Int,
    @SerializedName("order")
    val order: Int,
    @SerializedName("visible")
    val visible: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("cover")
    val cover: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("lisense")
    val lisense: String? = null,
    @SerializedName("lisenseLink")
    val lisenseLink: String? = null,
    @SerializedName("userControlSetTop")
    val userControlSetTop: Boolean = false,
    @SerializedName("articleList")
    val articleList: List<Any>? = null,
    @SerializedName("children")
    val children: List<Any>? = null
)