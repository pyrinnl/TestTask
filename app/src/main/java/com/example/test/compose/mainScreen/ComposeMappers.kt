package com.example.test.compose.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.test.R
import com.example.test.compose.theme.Teal200
import com.example.test.compose.theme.COLOR_ABOUT_BASE
import com.example.test.compose.theme.COLOR_ABOUT_TORE
import com.example.test.compose.theme.COLOR_MAP
import com.example.test.compose.theme.COLOR_WEATHER
import com.example.test.utils.BaseMapper

internal class DrawableMapper:BaseMapper<String,Int>() {

    override fun map(it: String): Int =
        when (it) {
            "rst-weather-cloudy" -> R.drawable.ic_sun
            "rst-help" -> R.drawable.ic_about
            "rst-play-circle" -> R.drawable.ic_play
            "rst-map_marker_path" -> R.drawable.ic_destination
            else -> R.drawable.ic_error
        }
}

/**
 * Маппер для цвета из запроса
 */
@Composable
fun ColorMapper(it: String): Color =
    when(it){
        "g-13" -> COLOR_WEATHER
        "g-12" -> COLOR_ABOUT_BASE
        "g-19" -> COLOR_ABOUT_TORE
        "g-11" -> COLOR_MAP
        else -> Teal200
    }