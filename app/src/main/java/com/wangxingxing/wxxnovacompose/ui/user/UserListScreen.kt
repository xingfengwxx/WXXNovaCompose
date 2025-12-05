package com.wangxingxing.wxxnovacompose.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wangxingxing.wxxnovacompose.data.remote.model.UserResponse
import com.wangxingxing.wxxnovacompose.ui.theme.WXXNovaComposeTheme
import com.wangxingxing.wxxnovacompose.utils.GlideImageLoader

/**
 * author : 王星星
 * date : 2024-09-03 17:20:00
 * email : 1099420259@qq.com
 * description : 用户列表页面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListPage(
    viewModel: UserViewModel = hiltViewModel()
) {
    val usersState by viewModel.usersState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "用户列表")
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                when (usersState) {
                    is UserViewModel.UserListState.Loading -> {
                        // 加载中状态
                        LoadingState()
                    }
                    is UserViewModel.UserListState.Success -> {
                        // 成功状态，展示用户列表
                        val users = (usersState as UserViewModel.UserListState.Success).users
                        UserList(users)
                    }
                    is UserViewModel.UserListState.Error -> {
                        // 错误状态
                        val message = (usersState as UserViewModel.UserListState.Error).message
                        ErrorState(message = message) {
                            viewModel.loadUsers()
                        }
                    }
                }
            }
        }
    )
}

/**
 * 加载中状态
 */
@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

/**
 * 错误状态
 */
@Composable
private fun ErrorState(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text(text = "重试")
            }
        }
    }
}

/**
 * 用户列表
 */
@Composable
private fun UserList(users: List<UserResponse>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(users) {
            UserItem(user = it)
        }
    }
}

/**
 * 用户列表项
 */
@Composable
private fun UserItem(user: UserResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 用户头像
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp)
            ) {
                GlideImageLoader.LoadImage(
                    url = user.avatarUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // 用户信息
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * 用户列表页面预览
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserListPagePreview() {
    WXXNovaComposeTheme {
        UserListPage()
    }
}
