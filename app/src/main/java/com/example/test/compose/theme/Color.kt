package com.example.test.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val COLOR_WEATHER
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF83600) else Color(0xFFF83600)
val COLOR_MAP
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF637B95) else Color(0xFF29323C)
val COLOR_ABOUT_BASE
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1B3C5A) else Color(0xFF5691C8)
val COLOR_ABOUT_TORE
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF60778C) else Color(0xFF3C5F80)