package com.wangxingxing.wxxnovacompose.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * 文章分页数据
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

/**
 * 文章数据模型
 */
data class Article(
    @SerializedName("adminAdd")
    val adminAdd: Boolean = false,
    @SerializedName("apkLink")
    val apkLink: String? = null,
    @SerializedName("audit")
    val audit: Int = 0,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("canEdit")
    val canEdit: Boolean = false,
    @SerializedName("chapterId")
    val chapterId: Int = 0,
    @SerializedName("chapterName")
    val chapterName: String? = null,
    @SerializedName("collect")
    val collect: Boolean = false,
    @SerializedName("courseId")
    val courseId: Int = 0,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("descMd")
    val descMd: String? = null,
    @SerializedName("envelopePic")
    val envelopePic: String? = null,
    @SerializedName("fresh")
    val fresh: Boolean = false,
    @SerializedName("host")
    val host: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isAdminAdd")
    val isAdminAdd: Boolean = false,
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("niceDate")
    val niceDate: String? = null,
    @SerializedName("niceShareDate")
    val niceShareDate: String? = null,
    @SerializedName("origin")
    val origin: String? = null,
    @SerializedName("prefix")
    val prefix: String? = null,
    @SerializedName("projectLink")
    val projectLink: String? = null,
    @SerializedName("publishTime")
    val publishTime: Long = 0L,
    @SerializedName("realSuperChapterId")
    val realSuperChapterId: Int = 0,
    @SerializedName("selfVisible")
    val selfVisible: Int = 0,
    @SerializedName("shareDate")
    val shareDate: Long = 0L,
    @SerializedName("shareUser")
    val shareUser: String? = null,
    @SerializedName("superChapterId")
    val superChapterId: Int = 0,
    @SerializedName("superChapterName")
    val superChapterName: String? = null,
    @SerializedName("tags")
    val tags: List<ArticleTag>? = null,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("visible")
    val visible: Int = 0,
    @SerializedName("zan")
    val zan: Int = 0
)

/**
 * 文章标签
 */
data class ArticleTag(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)
