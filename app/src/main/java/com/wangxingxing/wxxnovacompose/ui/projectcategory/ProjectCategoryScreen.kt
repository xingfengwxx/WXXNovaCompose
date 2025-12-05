package com.wangxingxing.wxxnovacompose.ui.projectcategory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wangxingxing.wxxnovacompose.base.UiState
import com.wangxingxing.wxxnovacompose.data.local.db.entity.ProjectCategoryEntity
import com.wangxingxing.wxxnovacompose.ui.theme.WXXNovaComposeTheme
import com.wangxingxing.wxxnovacompose.R



/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 项目分类页面（使用 Material 3 风格的下拉刷新动画）
 * 
 * 使用 PullToRefreshBox composable，它默认就集成了官方的 Material 3 风格动画
 * 下拉刷新时，不管本地是否有数据都请求网络
 */
@Composable
fun ProjectCategoryScreen(
    viewModel: ProjectCategoryViewModel = hiltViewModel()
) {
    val categories by viewModel.categories.collectAsState()
    val uiState by viewModel.getUiState().collectAsState()

    // 判断是否正在刷新（Loading 状态）
    val isRefreshing = uiState is UiState.Loading

    // 获取字符串资源
    val title = stringResource(R.string.project_category_title)
    val emptyData = stringResource(R.string.project_category_empty)
    val loadingText = stringResource(R.string.project_category_loading)

    // 使用 PullToRefreshBox 包装组件（Material 3 风格）
    // 下拉刷新时，不管本地是否有数据都请求网络
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            // 下拉刷新时，不管本地是否有数据都请求网络
            viewModel.refresh()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 标题
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 加载状态（首次加载时显示）
            when (val state = uiState) {
                is UiState.Loading -> {
                    if (categories.isEmpty()) {
                        // 首次加载，显示加载指示器
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                CircularProgressIndicator()
                                Text(loadingText)
                            }
                        }
                    } else {
                        // 刷新中，显示列表（下拉刷新动画由 PullRefreshIndicator 处理）
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(categories) { category ->
                                ProjectCategoryCard(category = category)
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                is UiState.Success<*> -> {
                    if (categories.isEmpty()) {
                        // 空数据提示
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = emptyData,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    } else {
                        // 列表内容（支持下拉刷新）
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(categories) { category ->
                                ProjectCategoryCard(category = category)
                            }
                        }
                    }
                }
                else -> {
                    // Idle 状态，显示空数据
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = emptyData,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * PullToRefreshBox - Material 3 风格的下拉刷新包装组件
 * 
 * 这是一个便捷的包装组件，使用 Material 的 pullRefresh API，并应用 Material 3 风格
 * Material 的 pullRefresh API 提供了与 Material 3 兼容的动画效果
 * 
 * @param isRefreshing 是否正在刷新
 * @param onRefresh 刷新回调，下拉刷新时会调用此回调
 * @param modifier 修饰符
 * @param content 内容
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // 下拉刷新状态
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        content()

        // 下拉刷新指示器
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * 项目分类卡片
 */
@Composable
fun ProjectCategoryCard(
    category: ProjectCategoryEntity
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 分类名称
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // 描述信息
            if (!category.desc.isNullOrEmpty()) {
                Text(
                    text = category.desc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 详细信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.project_category_id)}: ${category.id}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "${stringResource(R.string.project_category_order)}: ${category.order}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.project_category_visible)}: ${if (category.visible == 1) stringResource(R.string.project_category_visible_yes) else stringResource(R.string.project_category_visible_no)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "${stringResource(R.string.project_category_type)}: ${category.type}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

/**
 * 项目分类卡片预览
 */
@Preview(showBackground = true)
@Composable
fun ProjectCategoryCardPreview() {
    WXXNovaComposeTheme {
        ProjectCategoryCard(
            category = ProjectCategoryEntity(
                id = 294,
                name = "完整项目",
                courseId = 13,
                parentChapterId = 293,
                order = 145000,
                visible = 0,
                type = 0,
                desc = "这是一个项目分类的示例描述",
                cover = "",
                author = "",
                lisense = "",
                lisenseLink = "",
                userControlSetTop = false
            )
        )
    }
}

