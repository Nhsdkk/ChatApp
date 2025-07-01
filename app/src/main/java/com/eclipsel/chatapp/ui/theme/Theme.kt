package com.eclipsel.chatapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    surface = BlackSolidPrimary,
    onSurface = WhiteSolidPrimary,
    onBackground = WhiteSolidPrimary,
    error = RedSolidPrimary,
    surfaceVariant = BlackTransparent80,
    onSurfaceVariant = WhiteTransparent80,
    background = BlackSolidPrimary
)

private val LightColorScheme = lightColorScheme(
    surface = WhiteSolidPrimary,
    onSurface = BlackSolidPrimary,
    onBackground = BlackSolidPrimary,
    error = RedSolidPrimary,
    surfaceVariant = WhiteTransparent80,
    onSurfaceVariant = BlackTransparent80,
    background = WhiteSolidPrimary
)

@Composable
fun extendColor(light: Color, dark: Color, isSystemDarkTheme: Boolean = isSystemInDarkTheme()) : Color {
    return if (isSystemDarkTheme) dark else light
}

@Composable
fun extendBrush(light: Brush, dark: Brush, isDarkTheme: Boolean = isSystemInDarkTheme()) : Brush {
    return if (isDarkTheme) dark else light
}

val ColorScheme.MessageFemale @Composable get() = extendColor(
    PinkQuaternaryTransparent50, 
    PinkQuaternaryTransparent50
)

val ColorScheme.MessageMale @Composable get() = extendColor(
    BlueSecondaryTransparent50,
    BlueSecondaryTransparent50
)

val ColorScheme.Background @Composable get() = extendBrush(
    Brush.linearGradient(
        colors = listOf(
            PinkSolidTertiery,
            BlueSolidTertiery,
            PinkSolidTertiery,
        ),
        start = Offset.Zero
    ),
    Brush.linearGradient(
        colors = listOf(
            PinkSolidTertiery,
            BlueSolidTertiery,
            PinkSolidTertiery,
        ),
        start = Offset.Zero
    )
)

val ColorScheme.BackgroundFemale @Composable get() = extendBrush(
    Brush.linearGradient(
        colors = listOf(
            PinkSolidQuaternary,
            WhiteSolidPrimary,
            PinkSolidQuaternary,
        ),
        start = Offset.Zero
    ),
    Brush.linearGradient(
        colors = listOf(
            PinkSolidQuaternary,
            WhiteSolidPrimary,
            PinkSolidQuaternary,
        ),
        start = Offset.Zero
    )
)

val ColorScheme.BackgroundMale @Composable get() = extendBrush(
    Brush.linearGradient(
        colors = listOf(
            PinkSolidQuaternary,
            BlueSolidQuinary,
            BlueSolidQuaternary,
        ),
        start = Offset.Zero
    ),
    Brush.linearGradient(
        colors = listOf(
            PinkSolidQuaternary,
            BlueSolidQuinary,
            BlueSolidQuaternary,
        ),
        start = Offset.Zero
    )
)

@Composable
fun ChatAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}