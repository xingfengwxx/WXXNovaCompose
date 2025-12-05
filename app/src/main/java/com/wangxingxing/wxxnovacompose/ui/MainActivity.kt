package com.wangxingxing.wxxnovacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.wangxingxing.wxxnovacompose.navigation.AppNavigation
import com.wangxingxing.wxxnovacompose.ui.components.BottomNavigationBar
import com.wangxingxing.wxxnovacompose.ui.theme.WXXNovaComposeTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * author : 王星星
 * date : 2025/12/5 14:45
 * email : 1099420259@qq.com
 * description : 主界面
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WXXNovaComposeTheme {
                val navController = rememberNavController()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    BottomNavigationBar(navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    WXXNovaComposeTheme {
        val navController = rememberNavController()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            AppNavigation(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            BottomNavigationBar(navController = navController)
        }
    }
}