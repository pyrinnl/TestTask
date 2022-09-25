package com.example.test.model

import com.example.test.model.entities.main.MainPage
import com.example.test.retrofit.MainPageApi
import com.example.test.retrofit.main.views.MainDataViews
import com.example.test.retrofit.main.views.Stub
import com.example.test.utils.ThisApplicationNetworkErrors.unknownError
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class MainScreenRepository @Inject constructor(
    private val mainPageApi: MainPageApi
) {

    suspend fun getMainPage(): MainResponse {
        return try {
            val mainPage = mainPageApi.getMainPage()
            if (mainPage.success.not()) {
                MainResponse.Error(mainPage.error ?: unknownError)
            } else {
                val resultList = mutableListOf<MainDataViews>()
                mainPage.data.content.forEach {
                    try {
                        resultList.add(
                            MainDataViews(
                                title = it.title,
                                template = it.template,
                                url = it.url,
                                mainScreenViewsList = when (it.template.obj) {
                                    Type.OBJECT.value -> {
                                        mainPageApi.getObjectsItems(it.url)
                                    }
                                    Type.ROOM.value -> {
                                        mainPageApi.getRoomsItems(it.url)
                                    }
                                    Type.TOUR.value -> {
                                        mainPageApi.getToursItems(it.url)
                                    }
                                    Type.BLOG.value -> {
                                        mainPageApi.getBlogItems(it.url)
                                    }
                                    else -> Stub
                                }
                            )
                        )
                    } catch (e: Throwable) {
                        return MainResponse.Error(e.localizedMessage ?: unknownError)
                    }
                }
                MainResponse.Success(
                    MainPage(
                        buttonsList = mainPage.data.buttons,
                        viewsList = resultList
                    )
                )
            }
        } catch (e: Throwable) {
            MainResponse.Error(e.localizedMessage ?: unknownError)
        }
    }

    sealed class MainResponse {
        data class Error(val message: String) : MainResponse()
        data class Success(val mainPage: MainPage) : MainResponse()
    }

    /**
     * Список известых типов из ответа сервера
     */
    private enum class Type(val value: String) {
        OBJECT(value = "object"),
        ROOM(value = "room"),
        TOUR(value = "tour"),
        BLOG(value = "blog")
    }
}