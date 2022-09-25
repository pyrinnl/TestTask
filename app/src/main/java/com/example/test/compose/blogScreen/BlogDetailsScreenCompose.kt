package com.example.test.compose.blogScreen


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
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
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillWidth
                ),
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
    Text(title)
    Text(subtitle)
}

@Composable
internal fun BlogContent(content: String) {
    Text(content)
}

@Composable
internal fun PlaceHolder() {
    Surface(
        color = MaterialTheme.colors.primarySurface,
    modifier = Modifier.fillMaxWidth()) {

    }
}

@Preview
@Composable
internal fun PreviewPlaceHolder() {
    PlaceHolder()
}





