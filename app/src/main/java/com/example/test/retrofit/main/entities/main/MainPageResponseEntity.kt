package com.example.test.retrofit.main.entities.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MainPageResponseEntity(
    val success: Boolean,
    val error: String?,
    val time: String,
    val data: Data
) {

    @Serializable
    data class Data(
        val buttons: List<Button>,
        val content: List<Content>
    )

    @Serializable
    data class Button(
        val icon: String,
        val color: String,
        val title: String,
        val type: String,
        val url: String
    )

    @Serializable
    data class Content(
        val title: String,
        val template: Template,
        val url: String
    )

    @Serializable
    data class Template(
        val card: String,
        @SerialName("object") val obj: String,
        val size: String,
        val direction: String
    )
}
