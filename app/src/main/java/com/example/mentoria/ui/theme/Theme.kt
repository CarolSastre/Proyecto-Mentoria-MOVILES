package com.example.mentoria.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    /*
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80*/
    primary = MikadoYellow,
    onPrimary = RichBlack,
    primaryContainer = RoyalBlue,
    onPrimaryContainer = Color.White,
    secondary = GoldenGoldenrod,
    onSecondary = RichBlack,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    outline = RoyalBlue
)

private val LightColorScheme = lightColorScheme(
    /*
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40*/
    primary = GoldenGoldenrod,       // Un dorado un poco más sobrio para botones
    onPrimary = Color.White,
    primaryContainer = MikadoYellow, // Dorado brillante para destacar
    onPrimaryContainer = RichBlack,
    secondary = OxfordBlue,          // El azul oscuro ahora es un acento serio
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = DeepBlueText,
    surface = LightSurface,
    onSurface = DeepBlueText,
    outline = RoyalBlue
)

@Composable
fun MentoriaTheme(
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