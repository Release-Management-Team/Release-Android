package com.sogang.release.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sogang.release.ReleaseTabBar
import com.sogang.release.ui.login.LoginScreen
import com.sogang.release.ui.login.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
//        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("main") { ReleaseTabBar() }
    }
}