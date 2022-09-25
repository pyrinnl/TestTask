package com.example.test.compose.mainScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.model.entities.main.MainPage
import com.example.test.retrofit.main.entities.main.MainPageResponseEntity
import com.example.test.retrofit.main.views.*
import com.example.test.retrofit.main.views.Stub
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun LoadedViewState(mainPage: MainPage) {

    LazyColumn {
        item {
            Text(
                text = "Главная",
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                fontSize = 20.sp
            )
        }
        item {
            ButtonView(buttonList = mainPage.buttonsList)
        }

        items(mainPage.viewsList) { mainDataViews ->
            Header(mainDataViews.title)
            when (mainDataViews.mainScreenViewsList) {
                is BlogItemResponseEntity -> {
                    val performanceMainView = getPerformanceMainView(mainDataViews.template)
                    mainDataViews.mainScreenViewsList.data.forEach { item ->
                        when (performanceMainView) {
                            CardHorizontal -> {
                                /* неподдерживаемый кейс */
                            }
                            CardHorizontalFillVertical -> {
                                createCardHorizontalFillVertical(
                                    imageUrl = item.image.lg,
                                    title = item.title,
                                    subtitle = item.subtitle,
                                    date = item.date.date,
                                    onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                )
                            }
                            CardVertical -> {
                                /* неподдерживаемый кейс */
                            }
                            ColumnHorizontal -> {
                                /* неподдерживаемый кейс */
                            }
                            ColumnVertical -> {
                                /* неподдерживаемый кейс */
                            }
                            com.example.test.compose.mainScreen.Stub -> {
                                /* неподдерживаемый кейс */
                            }
                            CardHorizontalFillHorizontal -> {
                                /* неподдерживаемый кейс */
                            }
                        }
                    }
                }
                is ObjectResponseEntity -> {
                    when (getPerformanceMainView(mainDataViews.template)) {
                        CardHorizontal -> {
                            /* неподдерживаем кейс */
                        }
                        CardHorizontalFillVertical -> {
                            /* неподдерживаем кейс */
                        }
                        CardVertical -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, start = 8.dp),
                            ) {
                                mainDataViews.mainScreenViewsList.data.forEach { item ->
                                    CreateCardVertical(
                                        imageUrl = item.image.md,
                                        title = item.title,
                                        subtitle = item.subtitle,
                                        description = null,
                                        onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                    )
                                }
                            }
                        }
                        ColumnHorizontal -> {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                            ) {
                                item { Spacer(modifier = Modifier.padding(start = 8.dp)) }
                                items(mainDataViews.mainScreenViewsList.data) { item ->
                                    CreateColumnHorizontal(
                                        imageUrl = item.image.md,
                                        title = item.title,
                                        subtitle = item.subtitle,
                                        description = null,
                                        onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                    )
                                }
                            }
                        }
                        ColumnVertical -> {
                            LazyVerticalGrid(
                                userScrollEnabled = false,
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.height(505.dp)
                                /**
                                 * Не понял как сделать обычный VerticalGrid
                                 * Ловил краш из-за того, что пытаюсь LazyVerticalGrid положить в другой lazy,
                                 * пришлось захордкодить выстоту и взять только 6 элементов
                                 */
                            ) {
                                items(
                                    items = mainDataViews.mainScreenViewsList.data.take(6)
                                ) { item ->
                                    CreateColumnVertical(
                                        imageUrl = item.image.md,
                                        title = item.title,
                                        subtitle = item.subtitle,
                                        description = null,
                                        onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                    )
                                }
                            }
                        }
                        com.example.test.compose.mainScreen.Stub -> {
                            /* неподдерживаемый кейс */
                        }
                        CardHorizontalFillHorizontal -> {
                            /* неподдерживаем кейс */
                        }
                    }

                }
                is RoomsResponseEntity -> {
                    when (getPerformanceMainView(mainDataViews.template)) {
                        CardHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        CardHorizontalFillHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        CardHorizontalFillVertical -> {
                            /* неподдерживаемый кейс */
                        }
                        CardVertical -> {
                            LazyVerticalGrid(
                                userScrollEnabled = false,
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.height(505.dp)
                                /**
                                 * Не понял как сделать обычный VerticalGrid
                                 * Ловил краш из-за того, что пытаюсь LazyVerticalGrid положить в другой lazy,
                                 * пришлось захордкодить выстоту и взять только 6 элементов
                                 */
                            ) {
                                items(
                                    items = mainDataViews.mainScreenViewsList.data.take(6)
                                ) { item ->
                                    CreateColumnVertical(
                                        imageUrl = item.image.md,
                                        title = item.title,
                                        subtitle = item.type,
                                        description = null,
                                        onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                    )
                                }
                            }
                        }
                        ColumnHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        ColumnVertical -> {
                            /* неподдерживаемый кейс */
                        }
                        com.example.test.compose.mainScreen.Stub -> {
                            /* неподдерживаемый кейс */
                        }
                    }
                }
                is ToursResponseEntity -> {
                    when (getPerformanceMainView(mainDataViews.template)) {
                        CardHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        CardHorizontalFillHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        CardHorizontalFillVertical -> {
                            /* неподдерживаемый кейс */
                        }
                        CardVertical -> {
                            /* неподдерживаемый кейс */
                        }
                        ColumnHorizontal -> {
                            /* неподдерживаемый кейс */
                        }
                        ColumnVertical -> {
                            LazyVerticalGrid(
                                userScrollEnabled = false,
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.height(505.dp)
                                /**
                                 * Не понял как сделать обычный VerticalGrid
                                 * Ловил краш из-за того, что пытаюсь LazyVerticalGrid положить в другой lazy,
                                 * пришлось захордкодить выстоту и взять только 6 элементов
                                 */
                            ) {
                                items(
                                    items = mainDataViews.mainScreenViewsList.data.take(6)
                                ) { item ->
                                    CreateColumnVertical(
                                        imageUrl = item.image.md,
                                        title = item.title,
                                        subtitle = item.location,
                                        description = null,
                                        onClick = { mainDataViews.onClick?.let { it(item.id) } }
                                    )
                                }
                            }
                        }
                        com.example.test.compose.mainScreen.Stub -> {
                            /* неподдерживаемый кейс */
                        }
                    }
                }
                Stub -> {}
            }
        }
    }
}

/**
 * Смотреть [ColumnVertical]
 */
@Composable
internal fun CreateColumnVertical(
    imageUrl: String,
    title: String,
    subtitle: String,
    description: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .clickable { onClick() }
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        GlideImage(
            imageModel = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillHeight
            ),
        )
        Text(
            text = title,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = subtitle,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(top = 2.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Смотреть [ColumnHorizontal]
 */
@Composable
internal fun CreateColumnHorizontal(
    imageUrl: String,
    title: String,
    subtitle: String,
    description: String?,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(96.dp)
            .padding(end = 8.dp)
            .width(304.dp)
            .clickable { onClick() }
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 8.dp)
                .size(80.dp)
                .aspectRatio(1f)
        ) {
            GlideImage(
                imageModel = imageUrl,
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillHeight
                )
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Смотреть [CardHorizontalFillVertical]
 */
@Composable
internal fun createCardHorizontalFillVertical(
    imageUrl: String,
    title: String,
    subtitle: String,
    date: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
    ) {
        Surface(shape = RoundedCornerShape(16.dp)) {
            GlideImage(
                imageModel = imageUrl,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillHeight
                ),
            )
        }
        Text(
            text = date,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = subtitle,
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * Смотреть [CardVertical]
 */
@Composable
internal fun CreateCardVertical(
    imageUrl: String,
    title: String,
    subtitle: String,
    description: String?,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(96.dp)
            .padding(end = 8.dp)
            .fillMaxHeight()
            .clickable { onClick() }
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 8.dp)
                .size(80.dp)
                .aspectRatio(1f)
        ) {
            GlideImage(
                imageModel = imageUrl,
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillHeight
                )
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
internal fun ButtonView(buttonList: List<MainPageResponseEntity.Button>) {
    val drawableMapper = DrawableMapper()
    LazyRow(
        modifier = Modifier.padding(top = 24.dp)
    ) {
        item { Spacer(modifier = Modifier.width(16.dp)) }
        buttonList.forEach { button ->
            item {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorMapper(button.color)
                    )
                ) {
                    Icon(
                        painter = painterResource(drawableMapper(button.icon)),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(20.dp)
                    )
                    Text(text = button.title, fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
internal fun Header(text: String) {
    Text(text = text, modifier = Modifier.padding(start = 16.dp, top = 24.dp), fontSize = 24.sp)
}


