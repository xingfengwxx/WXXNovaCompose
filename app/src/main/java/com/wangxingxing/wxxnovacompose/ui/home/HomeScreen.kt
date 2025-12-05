package com.wangxingxing.wxxnovacompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.wangxingxing.wxxnovacompose.R
import com.wangxingxing.wxxnovacompose.base.UiState
import com.wangxingxing.wxxnovacompose.data.remote.model.Article
import com.wangxingxing.wxxnovacompose.data.remote.model.Banner
import com.wangxingxing.wxxnovacompose.ui.theme.WXXNovaComposeTheme
import java.net.URLEncoder

/**
 * author : 王星星
 * date : 2025/01/20
 * email : 1099420259@qq.com
 * description : 首页 Compose 页面
 */
@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val banners by viewModel.banners.collectAsState()
    val uiState by viewModel.getUiState().collectAsState()
    val articles = viewModel.articles.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Banner 区域
        when (val state = uiState) {
            is UiState.Loading -> {
                // 加载中，显示占位
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> {
                // 错误状态，显示错误信息
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.errorContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
            else -> {
                // 显示 Banner
                if (banners.isNotEmpty()) {
                    BannerView(
                        banners = banners,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // 文章列表
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = articles.itemCount,
                key = { index -> articles[index]?.id ?: index }
            ) { index ->
                val article = articles[index]
                if (article != null) {
                    ArticleItem(
                        article = article,
                        onClick = {
                            // 跳转到文章详情页
                            navController?.let { nav ->
                                val articleJson = Gson().toJson(article)
                                val encodedJson = URLEncoder.encode(articleJson, "UTF-8")
                                nav.navigate("article_detail/$encodedJson")
                            }
                        }
                    )
                }
            }

            // 加载状态
            when {
                articles.loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                articles.loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                articles.loadState.refresh is LoadState.Error -> {
                    val error = articles.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.message ?: stringResource(R.string.error_unknown),
                            onRetry = { articles.retry() }
                        )
                    }
                }
                articles.loadState.append is LoadState.Error -> {
                    val error = articles.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.message ?: stringResource(R.string.error_unknown),
                            onRetry = { articles.retry() }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Banner 视图（带指示器，支持手动滑动）
 */
@Composable
fun BannerView(
    banners: List<Banner>,
    modifier: Modifier = Modifier
) {
    if (banners.isEmpty()) {
        return
    }

    val actualPageCount = banners.size
    val isSinglePage = actualPageCount <= 1

    // Pager 状态
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { actualPageCount }
    )

    // 当前显示的页面索引（用于指示器）
    val currentPage = pagerState.currentPage

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 8.dp)
    ) {
        // Banner 轮播
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
            key = { index -> banners[index].id } // 使用唯一 ID 作为 key，优化重组
        ) { page ->
            val banner = banners[page]
            BannerItem(
                banner = banner,
                modifier = Modifier.fillMaxSize()
            )
        }

        // 指示器
        if (!isSinglePage) {
            BannerIndicator(
                pageCount = actualPageCount,
                currentPage = currentPage,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

/**
 * Banner 指示器
 */
@Composable
fun BannerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(
                        width = if (index == currentPage) 24.dp else 8.dp,
                        height = 8.dp
                    )
                    .clip(CircleShape)
                    .background(
                        color = if (index == currentPage) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        }
                    )
            )
        }
    }
}

/**
 * Banner 项
 */
@Composable
fun BannerItem(
    banner: Banner,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = banner.imagePath,
                contentDescription = banner.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // 标题覆盖层
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = banner.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * 文章项
 */
@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 标题
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // 描述
            if (!article.desc.isNullOrEmpty()) {
                Text(
                    text = article.desc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // 底部信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 作者和分类
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!article.author.isNullOrEmpty()) {
                        Text(
                            text = article.author,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    if (!article.chapterName.isNullOrEmpty()) {
                        Text(
                            text = article.chapterName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // 时间
                if (!article.niceDate.isNullOrEmpty()) {
                    Text(
                        text = article.niceDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

/**
 * Banner 视图预览（带指示器，支持手动滑动）
 */
@Preview(showBackground = true, widthDp = 360, heightDp = 200)
@Composable
fun BannerViewPreview() {
    WXXNovaComposeTheme {
        BannerView(
            banners = listOf(
                Banner(
                    desc = "Banner 描述 1",
                    id = 1,
                    imagePath = "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
                    isVisible = 1,
                    order = 1,
                    title = "Banner 标题 1",
                    type = 0,
                    url = "https://www.wanandroid.com"
                ),
                Banner(
                    desc = "Banner 描述 2",
                    id = 2,
                    imagePath = "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
                    isVisible = 1,
                    order = 2,
                    title = "Banner 标题 2",
                    type = 0,
                    url = "https://www.wanandroid.com"
                ),
                Banner(
                    desc = "Banner 描述 3",
                    id = 3,
                    imagePath = "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
                    isVisible = 1,
                    order = 3,
                    title = "Banner 标题 3",
                    type = 0,
                    url = "https://www.wanandroid.com"
                )
            )
        )
    }
}

/**
 * Banner 指示器预览
 */
@Preview(showBackground = true)
@Composable
fun BannerIndicatorPreview() {
    WXXNovaComposeTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BannerIndicator(
                pageCount = 3,
                currentPage = 0
            )
            BannerIndicator(
                pageCount = 3,
                currentPage = 1
            )
            BannerIndicator(
                pageCount = 3,
                currentPage = 2
            )
        }
    }
}

/**
 * Banner 项预览
 */
@Preview(showBackground = true)
@Composable
fun BannerItemPreview() {
    WXXNovaComposeTheme {
        BannerItem(
            banner = Banner(
                desc = "Banner 描述",
                id = 1,
                imagePath = "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
                isVisible = 1,
                order = 1,
                title = "Banner 标题示例",
                type = 0,
                url = "https://www.wanandroid.com"
            ),
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
        )
    }
}

/**
 * 文章项预览
 */
@Preview(showBackground = true)
@Composable
fun ArticleItemPreview() {
    WXXNovaComposeTheme {
        ArticleItem(
            article = Article(
                id = 1,
                title = "示例文章标题",
                desc = "这是一段示例描述文本，用于展示文章摘要内容。",
                author = "王星星",
                chapterName = "技术分享",
                niceDate = "2025-01-20"
            )
        )
    }
}

/**
 * 错误项预览
 */
@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    WXXNovaComposeTheme {
        ErrorItem(
            message = "网络请求失败，请检查网络连接",
            onRetry = {}
        )
    }
}


/**
 * 错误项
 */
@Composable
fun ErrorItem(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = onRetry) {
            Text(stringResource(R.string.home_retry))
        }
    }
}
