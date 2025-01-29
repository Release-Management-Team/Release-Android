package com.sogang.release.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = lightColorScheme(
    primary = Primary1,
    onPrimary = Gray1,
    secondary = Primary2,
    onSecondary = Gray1,
    background = Gray2,
    onBackground = Black1,
    surface = Gray3,
    onSurface = Black2,
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary2,
    onPrimary = Gray1,
    secondary = Primary3,
    onSecondary = Gray1,
    background = Black1,
    onBackground = Gray2,
    surface = Black2,
    onSurface = Gray3,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false, // 다크 모드 지원
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val appColors = AppColors(
        primary1 = Primary1,
        primary2 = Primary2,
        primary3 = Primary3,
        primary4 = Primary4,
        gray1 = Gray1,
        gray2 = Gray2,
        gray3 = Gray3,
        gray4 = Gray4,
        gray5 = Gray5,
        black1 = Black1,
        black2 = Black2,
        black3 = Black3
    )

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography.material,
            content = content
        )
    }
}