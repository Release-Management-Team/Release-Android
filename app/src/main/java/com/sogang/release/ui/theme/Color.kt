package com.sogang.release.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Primary1 = Color(0xFFF6C015)
val Primary2 = Color(0xFF685722)
val Primary3 = Color(0xFFFFE38B)

val Gray1 = Color(0xFFFFFFFF)
val Gray2 = Color(0xFFEEEEEE)
val Gray3 = Color(0xFFD9D9D9)
val Gray4 = Color(0xFFB6B6B6)
val Gray5 = Color(0xFF999999)

val Black1 = Color(0xFF1C1C1C)
val Black2 = Color(0xFF292929)
val Black3 = Color(0xFF484848)

data class AppColors(
    val primary1: Color,
    val primary2: Color,
    val primary3: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,
    val black1: Color,
    val black2: Color,
    val black3: Color
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        primary1 = Primary1,
        primary2 = Primary2,
        primary3 = Primary3,
        gray1 = Gray1,
        gray2 = Gray2,
        gray3 = Gray3,
        gray4 = Gray4,
        gray5 = Gray5,
        black1 = Black1,
        black2 = Black2,
        black3 = Black3
    )
}

object AppThemeColors {
    val primary1: Color @Composable get() = LocalAppColors.current.primary1
    val primary2: Color @Composable get() = LocalAppColors.current.primary2
    val primary3: Color @Composable get() = LocalAppColors.current.primary3
    val gray1: Color @Composable get() = LocalAppColors.current.gray1
    val gray2: Color @Composable get() = LocalAppColors.current.gray2
    val gray3: Color @Composable get() = LocalAppColors.current.gray3
    val gray4: Color @Composable get() = LocalAppColors.current.gray4
    val gray5: Color @Composable get() = LocalAppColors.current.gray5
    val black1: Color @Composable get() = LocalAppColors.current.black1
    val black2: Color @Composable get() = LocalAppColors.current.black2
    val black3: Color @Composable get() = LocalAppColors.current.black3
}