package com.wangxingxing.wxxnovacompose.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.wxxnovacompose.base.BaseViewModel
import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import com.wangxingxing.wxxnovacompose.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * author : 王星星
 * date : 2024-09-03 17:15:00
 * email : 1099420259@qq.com
 * description : 用户页面的ViewModel，管理用户数据的状态
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    // 用户列表状态
    private val _usersState = MutableStateFlow<UserListState>(UserListState.Loading)
    val usersState: StateFlow<UserListState> = _usersState

    init {
        loadUsers()
    }

    /**
     * 加载用户列表
     */
    fun loadUsers() {
        _usersState.value = UserListState.Loading
        launch(block = {
            try {
                val users = userRepository.getUsersSuspend()
                _usersState.value = UserListState.Success(users)
            } catch (e: Exception) {
                LogUtils.e("Failed to load users: ${e.message}", e)
                _usersState.value = UserListState.Error(e.message ?: "Unknown error")
            }
        })
    }

    /**
     * 用户列表状态密封类
     */
    sealed class UserListState {
        object Loading : UserListState()
        data class Success(val users: List<UserResponse>) : UserListState()
        data class Error(val message: String) : UserListState()
    }
}
