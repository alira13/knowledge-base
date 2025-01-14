package com.example.jetpackcompose.inList.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    tertiary = Black,
    background = Black,
    surface = Black,
    onSecondary = White,
    onTertiary = White,
    onBackground = White,
    onSurface = White,

    //screen colors
    primary = DarkThemeDarkGreen,
    onPrimary = White,

    //button active
    primaryContainer = DarkThemeBrightGreen,
    onPrimaryContainer = White,

    //button not active
    secondaryContainer = LightThemeLightGreen,
    onSecondaryContainer = White
)

private val LightColorScheme = lightColorScheme(

    tertiary = White,
    background = White,
    surface = White,

    onSecondary = Black,
    onTertiary = Black,
    onBackground = Black,
    onSurface = Black,

    //screen colors
    primary = White,
    onPrimary = Black,

    //button active
    primaryContainer = LightThemeBrightGreen,
    onPrimaryContainer = White,

    //button not active
    secondaryContainer = LightThemeLightGreen,
    onSecondaryContainer = White
    )

@Composable
fun JetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}