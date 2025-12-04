package com.wangxingxing.wxxnovacompose.data.remote.model

import com.squareup.moshi.Json

/**
 * author : 王星星
 * date : 2024-09-03 16:00:00
 * email : 1099420259@qq.com
 * description : 用户响应数据模型
 */
data class UserResponse(
    @Json(name = "id")
    val id: Int,
    
    @Json(name = "name")
    val name: String,
    
    @Json(name = "email")
    val email: String,
    
    @Json(name = "avatar")
    val avatarUrl: String
)
