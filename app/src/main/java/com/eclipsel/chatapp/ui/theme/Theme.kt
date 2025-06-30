package com.eclipsel.chatapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = WhiteSolidPrimary,
    error = RedSolidPrimary,
)

private val LightColorScheme = lightColorScheme(
    primary = BlackSolidPrimary,
    error = RedSolidPrimary,
)

@Composable
fun extendColor(light: Color, dark: Color, isSystemDarkTheme: Boolean = isSystemInDarkTheme()) : Color {
    return if (isSystemDarkTheme) dark else light
}

val ColorScheme.MessageFemale @Composable get() = extendColor(
    PinkQuaternaryTransparent50, 
    PinkQuaternaryTransparent50
)

val ColorScheme.MessageMale @Composable get() = extendColor(
    BlueSecondaryTransparent50,
    BlueSecondaryTransparent50
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