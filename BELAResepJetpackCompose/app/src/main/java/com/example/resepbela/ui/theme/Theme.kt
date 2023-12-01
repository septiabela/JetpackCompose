package com.example.resepbela.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Resep200, // Menggunakan Resep200 yang sudah Anda deklarasikan sebelumnya
    primaryVariant = Resep700, // Menggunakan Resep700 yang sudah Anda deklarasikan sebelumnya
    secondary = Resep500 // Menggunakan Resep500 yang sudah Anda deklarasikan sebelumnya
)

private val LightColorPalette = lightColors(
    primary = Resep500, // Menggunakan Resep500 yang sudah Anda deklarasikan sebelumnya
    primaryVariant = Resep700, // Menggunakan Resep700 yang sudah Anda deklarasikan sebelumnya
    secondary = Resep200 // Menggunakan Resep200 yang sudah Anda deklarasikan sebelumnya
)

@Composable
fun SubmissionComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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
