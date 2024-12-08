package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun ReleaseTabBar() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        NavHost(
            navController = navController,
            startDestination = ReleaseTabBarItem.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(ReleaseTabBarItem.Home.route) { HomeScreen() }
            composable(ReleaseTabBarItem.Search.route) { MyPageScreen() }
            composable(ReleaseTabBarItem.Feed.route) { MyPageScreen() }
            composable(ReleaseTabBarItem.MyPage.route) { MyPageScreen() }
        }

        // BottomNavigation at the bottom
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavigationBar(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = ReleaseTabBarItem.values()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White,
        modifier = Modifier.height(96.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == item.route) item.selectedIcon else item.normalIcon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color(0xFF00FF00) else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) Color(0xFF00FF00) else Color.Gray
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