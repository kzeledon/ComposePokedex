package com.kzeledon.jetpackcomposepokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = defaultBlue,
    background = darkBackground,
    onBackground = Color.White,
    surface = darkBackground,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = defaultBlue,
    background = lightBackground,
    onBackground = Color.Black,
    surface = lightBackground,
    onSurface = Color.Black
)

@Composable
fun JetpackComposePokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}