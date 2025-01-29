package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.sogang.release.ui.activity.ActivityDTO
import com.sogang.release.ui.activity.ActivityScreen
import com.sogang.release.ui.activityDetail.ActivityDetailScreen
import com.sogang.release.ui.book.BookScreen
import com.sogang.release.ui.notice.NoticeScreen
import com.sogang.release.ui.changePassword.ChangePasswordScreen

import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun ReleaseTabBar() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black2)
    ) {
        val currentRoute by navController.currentBackStackEntryAsState()
        val shouldShowTabBar = when (currentRoute?.destination?.route) {
            "notificationsScreen" -> false
            "activityDetailScreen" -> false
            "changePasswordScreen" -> false
            else -> true
        }

        val shouldShowTopBar = when (currentRoute?.destination?.route) {
            "notificationsScreen" -> true
            "changePasswordScreen" -> true
            else -> false
        }

        Column {
            if (shouldShowTopBar) {
                val title = when (currentRoute?.destination?.route) {
                    "notificationsScreen" -> "공지"
                    "changePasswordScreen" -> "비밀번호 변경"
                    else -> ""
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .background(AppThemeColors.black1)
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.backarrow),
                            contentDescription = "Back",
                            tint = AppThemeColors.gray1,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Text(
                        text = title,
                        style = AppTypography.heading4,
                        color = AppThemeColors.gray1,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                NavHost(
                    navController = navController,
                    startDestination = ReleaseTabBarItem.Home.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(ReleaseTabBarItem.Home.route) { HomeScreen(navController) }
                    composable(ReleaseTabBarItem.Activity.route) {
                        ActivityScreen { selectedActivity ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "selectedActivity",
                                selectedActivity
                            )
                            navController.navigate("activityDetailScreen")
                        }
                    }
                    composable(ReleaseTabBarItem.Book.route) { BookScreen() }
                    composable(ReleaseTabBarItem.MyPage.route) { MyPageScreen(navController) }
                    composable("notificationsScreen") { NoticeScreen() }
                    composable("activityDetailScreen") {
                        val selectedActivity =
                            navController.previousBackStackEntry?.savedStateHandle?.get<ActivityDTO>("selectedActivity")
                        if (selectedActivity != null) {
                            ActivityDetailScreen(
                                activity = selectedActivity,
                                onJoin = { /* Handle Join */ }
                            )
                        }
                    }
                    composable("changePasswordScreen") { ChangePasswordScreen(navController) }
                }
            }

            if (shouldShowTabBar) {
                BottomNavigationBar(navController)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = ReleaseTabBarItem.values()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = AppThemeColors.black2,
        contentColor = AppThemeColors.gray3,
        modifier = Modifier.height(96.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == item.route) item.selectedIcon else item.normalIcon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color(0xFF00FF00) else AppThemeColors.gray3
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) Color(0xFF00FF00) else AppThemeColors.gray3
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationRoute ?: "") {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}