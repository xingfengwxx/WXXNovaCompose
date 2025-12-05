package com.wangxingxing.wxxnovacompose.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 项目分类实体类，用于 Room 数据库存储
 */
@Entity(tableName = "project_category_table")
data class ProjectCategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val courseId: Int,
    val parentChapterId: Int,
    val order: Int,
    val visible: Int,
    val type: Int,
    val desc: String? = null,
    val cover: String? = null,
    val author: String? = null,
    val lisense: String? = null,
    val lisenseLink: String? = null,
    val userControlSetTop: Boolean = false
)

