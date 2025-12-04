package com.wangxingxing.wxxnovacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wangxingxing.wxxnovacompose.ui.theme.WXXNovaComposeTheme
import com.wangxingxing.wxxnovacompose.ui.user.UserListPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WXXNovaComposeTheme {
                UserListPage()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    WXXNovaComposeTheme {
        UserListPage()
    }
}