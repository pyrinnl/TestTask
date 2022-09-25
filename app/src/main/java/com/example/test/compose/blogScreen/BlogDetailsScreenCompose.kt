package com.example.test.compose.blogScreen


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.compose.mainScreen.ErrorViewState
import com.example.test.compose.mainScreen.LoadingViewState
import com.example.test.screens.main.blog.BlogDetailsViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun BlogDetailsScreenCompose(state: BlogDetailsViewModel.State) {

    when (state) {
        is BlogDetailsViewModel.State.Error -> ErrorViewState(
            error = state.error,
            onTryResubscribe = state.onTryResubscribe
        )
        is BlogDetailsViewModel.State.Loaded -> LoadedBlogViewState(state)
        BlogDetailsViewModel.State.Loading -> LoadingViewState()
    }
}

@Composable
internal fun LoadedBlogViewState(state: BlogDetailsViewModel.State.Loaded) {

    LazyColumn {
        item {
            GlideImage(
                imageModel = state.blogDetails.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        item {
            Text(state.blogDetails.date)
        }
        item {
            BlogTitleAndSubtitle(
                title = state.blogDetails.title,
                subtitle = state.blogDetails.subtitle
            )
        }
        item {
            BlogContent(content = state.blogDetails.content)
        }
    }
}

@Composable
internal fun BlogTitleAndSubtitle(title: String, subtitle: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp),

        )
    Text(
        text = subtitle,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp)
    )
}

@Composable
internal fun BlogContent(content: String) {
    Text(
        text = content,
        modifier = Modifier.padding(12.dp)
    )
}




