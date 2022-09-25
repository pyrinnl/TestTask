package com.example.test.compose.mainScreen

import com.example.test.retrofit.main.entities.main.MainPageResponseEntity

/**
 * Документации на то как должны распологатся элементы не нашёл, связал представления опираясь на видео и на ответ из запроса
 */
internal sealed class PerformanceMainView

/** Неизвестный кейс */
internal object CardHorizontal : PerformanceMainView()

/** 1 элемент во всю ширину */
internal object CardHorizontalFillVertical : PerformanceMainView()

/**  Неизвестный кейс */
internal object CardHorizontalFillHorizontal : PerformanceMainView()

/** 1 столбик */
internal object CardVertical : PerformanceMainView()

/** 1 линия со сролом по горизонтали */
internal object ColumnHorizontal : PerformanceMainView()

/** Два столбика */
internal object ColumnVertical : PerformanceMainView()

/** неподдерживаемый кейс */
internal object Stub : PerformanceMainView()

private const val HORIZONTAL = "horizontal"
private const val VERTICAL = "vertical"
private const val HORIZONTAL_FILL = "horizontal-fill"

/**
 * Опеределяем какой кейс представления
 */
internal fun getPerformanceMainView(template: MainPageResponseEntity.Template): PerformanceMainView = with(template) {
    return when{
        card == HORIZONTAL && direction == HORIZONTAL -> CardHorizontal
        card == HORIZONTAL && direction == VERTICAL -> CardVertical
        card == VERTICAL && direction == HORIZONTAL -> ColumnHorizontal
        card == VERTICAL && direction == VERTICAL -> ColumnVertical
        card == HORIZONTAL_FILL && direction == HORIZONTAL -> CardHorizontalFillVertical
        card == HORIZONTAL_FILL && direction == VERTICAL -> CardHorizontalFillVertical
        else -> Stub
    }
}

