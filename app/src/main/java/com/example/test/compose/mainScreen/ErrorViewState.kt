package com.example.test.compose.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.test.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

/**
 * Стейт ошибки
 */
@Composable
internal fun ErrorViewState(error: String, onTryResubscribe: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = MaterialTheme.colors.background,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    GlideImage(
                        imageModel = R.drawable.ic_error,
                        modifier = Modifier.size(128.dp),
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.FillWidth
                        )
                    )
                }
                Text(
                    text = error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                TextButton(
                    onClick = onTryResubscribe,
                    modifier = Modifier.background(MaterialTheme.colors.error)
                ) {
                    Text("Попробовать снова")
                }
            }
        }
    }
}
