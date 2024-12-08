package com.sogang.release.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CommonColorScheme = lightColorScheme(
    primary = Primary1,
    secondary = Primary2,
    tertiary = Primary3,
    background = Gray1,
    surface = Gray2,
    onPrimary = Black1,
    onSecondary = Gray3,
    onBackground = Black2,
    onSurface = Black3
)

@Composable
fun ReleaseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CommonColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}