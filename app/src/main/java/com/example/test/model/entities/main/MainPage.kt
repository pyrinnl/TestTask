package com.example.test.model.entities.main

import com.example.test.retrofit.main.entities.main.MainPageResponseEntity
import com.example.test.retrofit.main.views.MainDataViews

/**
 * @param buttonsList - список кнопок
 * @param viewsList - список моделей вью для отрисовки, смотреть [MainDataViews]
 */
internal data class MainPage(
    val buttonsList: List<MainPageResponseEntity.Button>,
    val viewsList: List<MainDataViews>,
)