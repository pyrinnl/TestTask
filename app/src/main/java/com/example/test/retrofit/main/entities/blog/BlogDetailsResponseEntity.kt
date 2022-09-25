package com.example.test.retrofit.main.entities.blog

import com.example.test.model.entities.blog.BlogDetails
import kotlinx.serialization.Serializable

@Serializable
data class BlogDetailsResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: Data
) {

    fun mapToBlogDetails(): BlogDetails {
        return BlogDetails(
            id = data.id,
            url = data.url,
            image = data.image.md,
            title = data.title,
            subtitle = data.subtitle,
            like = data.like,
            date = data.date,
            content = data.content
        )
    }

    @Serializable
    data class Data(
        val id: Int,
        val url: String,
        val image: Image,
        val title: String,
        val subtitle: String,
        val like: Int,
        val date: String,
        val content: String
    )

    @Serializable
    data class Image(
        val sm: String,
        val md: String,
        val lg: String
    )
}