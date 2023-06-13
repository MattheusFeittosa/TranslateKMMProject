package com.plcoding.translator_kmm.android.core.presentation

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.plcoding.translator_kmm.core.language.translate.presentation.Colors

object AndroidColorsAndThemes {
    val LightBlue = Color(Colors.LightBlue)
    val LightBlueGrey = Color(Colors.LightBlueGrey)
    val AccentViolet = Color(Colors.AccentViolet)
    val TextBlack = Color(Colors.TextBlack)
    val DarkGrey = Color(Colors.DarkGrey)

    val lightColors = lightColors(
        primary = AccentViolet,
        background = LightBlueGrey,
        onPrimary = Color.White,
        onBackground = TextBlack,
        surface = Color.White,
        onSurface = TextBlack
    )

    val darkColors = darkColors(
        primary = AccentViolet,
        background = DarkGrey,
        onPrimary = Color.White,
        onBackground = Color.White,
        surface = DarkGrey,
        onSurface = Color.White
    )
}
