package com.wangxingxing.wxxnovacompose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.wangxingxing.wxxnovacompose.data.remote.model.UserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2025/11/20 19:26
 * email : 1099420259@qq.com
 * description : 用户信息本地存储管理（使用 DataStore）
 */

private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

private val USER_INFO_KEY = stringPreferencesKey("user_info")

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    private val dataStore: DataStore<Preferences> = context.userDataStore

    /**
     * 获取用户信息
     */
    val userInfo: Flow<UserInfo?> = dataStore.data.map { preferences ->
        val userInfoJson = preferences[USER_INFO_KEY]
        if (userInfoJson.isNullOrBlank()) {
            null
        } else {
            try {
                gson.fromJson(userInfoJson, UserInfo::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * 保存用户信息
     */
    suspend fun saveUserInfo(userInfo: UserInfo) {
        dataStore.edit { preferences ->
            val userInfoJson = gson.toJson(userInfo)
            preferences[USER_INFO_KEY] = userInfoJson
        }
    }

    /**
     * 清除用户信息
     */
    suspend fun clearUserInfo() {
        dataStore.edit { preferences ->
            preferences.remove(USER_INFO_KEY)
        }
    }

    /**
     * 获取用户信息（一次性读取）
     */
    suspend fun getUserInfo(): UserInfo? {
        val preferences = dataStore.data.first()
        val userInfoJson = preferences[USER_INFO_KEY]
        return if (userInfoJson.isNullOrBlank()) {
            null
        } else {
            try {
                gson.fromJson(userInfoJson, UserInfo::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }
}

