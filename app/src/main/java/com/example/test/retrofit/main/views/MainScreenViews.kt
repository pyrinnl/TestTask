package com.example.test.retrofit.main.views

import com.example.test.retrofit.main.entities.main.MainPageResponseEntity
import kotlinx.serialization.Serializable

internal data class MainDataViews(
    val title: String,
    val template: MainPageResponseEntity.Template,
    val url: String,
    val mainScreenViewsList: MainScreenViews,
    var onClick: ((Int) -> Unit)? = null
)

internal sealed class MainScreenViews

/**
 * Заглушка на случай, если [com.example.test.retrofit.main.entities.main.MainPageResponseEntity.Template.obj] вернет неизвестный тип
 */
internal object Stub : MainScreenViews()

@Serializable
internal data class BlogItemResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: List<Data>
) : MainScreenViews() {

    @Serializable
    data class Data(
        val id: Int,
        val image: Image,
        val title: String,
        val subtitle: String,
        val view: Int,
        val like: Int,
        val date: Date
    )

    @Serializable
    data class Date(
        val typeDate: String,
        val date: String
    )
}

@Serializable
internal data class ObjectResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: List<Data>
) : MainScreenViews() {

    @Serializable
    internal data class Data(
        val id: Int,
        val image: Image,
        val title: String,
        val subtitle: String
    )
}


@Serializable
internal data class RoomsResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: List<Data>
) : MainScreenViews() {

    @Serializable
    internal data class Data(
        val id: Int,
        val image: Image,
        val title: String,
        val date: Date,
        val duration: Duration,
        val price: Price,
        val countTourist: Int,
        val type: String
    )

    @Serializable
    internal data class Duration(
        val night: Int
    )

    @Serializable
    internal data class Date(
        val typeDate: String
    )

    @Serializable
    internal data class Price(
        val factPrice: Int,
        val price: Int,
        val discount: String? = null,
        val currency: String,
        val typePrice: String
    )
}

@Serializable
internal data class Image(
    val lg: String,
    val md: String,
    val sm: String
)

@Serializable
internal class ToursResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: List<Data>
) : MainScreenViews() {

    @Serializable
    internal data class Data(
        val id: Int,
        val url: String,
        val image: Image,
        val title: String,
        val location: String,
        val date: Date,
        val duration: Duration,
        val price: RoomsResponseEntity.Price,
        val home: Home
    )

    @Serializable
    internal data class Date(
        val typeDate: String,
        val date: String
    )

    @Serializable
    internal data class Duration(
        val night: Int? = null,
        val day: Int? = null,
        val hour: Int? = null,
    )

    @Serializable
    internal data class Price(
        val factPrice: Int,
        val price: Int,
        val currency: String,
        val typePrice: String
    )

    @Serializable
    internal data class Home(
        val id: Int,
        val name: String,
        val type: String,
        val night: Int? = null,
        val url: String,
        val image: Image,
        val base: Base
    )

    @Serializable
    internal data class Base(
        val id: Int,
        val name: String,
        val url: String
    )
}
