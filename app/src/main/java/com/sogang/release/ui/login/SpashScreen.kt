package com.sogang.release.ui.login

import android.app.Activity
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*

@Composable
fun SplashScreen(navController: NavController) {
    var isAnimationFinished by remember { mutableStateOf(false) }

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("splash.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 1f,
        isPlaying = true
    )

    // 애니메이션 완료 시 자동으로 이동
    LaunchedEffect(progress) {
        if (progress == 1f) {
            isAnimationFinished = true
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(250.dp)
        )
    }
}