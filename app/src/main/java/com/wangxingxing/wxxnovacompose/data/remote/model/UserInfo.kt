package com.wangxingxing.wxxnovacompose.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * 登录用户信息
 */
data class UserInfo(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("nickname")
    val nickname: String? = null,
    @SerializedName("publicName")
    val publicName: String? = null,
    @SerializedName("token")
    val token: String? = null
)
