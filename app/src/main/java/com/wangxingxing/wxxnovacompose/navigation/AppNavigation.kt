package com.wangxingxing.wxxnovacompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wangxingxing.wxxnovacompose.ui.home.HomeScreen
import com.wangxingxing.wxxnovacompose.ui.projectcategory.ProjectCategoryScreen

/**
 * author : 王星星
 * date : 2025-01-20 15:30:00
 * email : 1099420259@qq.com
 * description : 应用导航路由定义
 */
object AppRoute {
    const val HOME = "home"
    const val PROJECT_CATEGORY = "project_category"
}

/**
 * author : 王星星
 * date : 2025-01-20 15:30:00
 * email : 1099420259@qq.com
 * description : 应用导航组件
 */
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.HOME,
        modifier = modifier
    ) {
        addHomeScreen()
        addProjectCategoryScreen()
    }
}

/**
 * 添加首页路由
 */
private fun NavGraphBuilder.addHomeScreen() {
    composable(AppRoute.HOME) {
        HomeScreen()
    }
}

/**
 * 添加项目分类路由
 */
private fun NavGraphBuilder.addProjectCategoryScreen() {
    composable(AppRoute.PROJECT_CATEGORY) {
        ProjectCategoryScreen()
    }
}
