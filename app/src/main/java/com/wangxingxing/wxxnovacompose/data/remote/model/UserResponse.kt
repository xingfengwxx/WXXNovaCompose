package com.wangxingxing.wxxnovacompose.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * author : 王星星
 * date : 2024-09-03 16:00:00
 * email : 1099420259@qq.com
 * description : 用户响应数据模型
 */
data class UserResponse(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("avatar")
    val avatarUrl: String
)
